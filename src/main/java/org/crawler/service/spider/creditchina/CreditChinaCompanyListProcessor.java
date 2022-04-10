package org.crawler.service.spider.creditchina;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.CredentialsProvider;
import org.crawler.entity.CreditChinaCompanyList;
import org.crawler.mapper.CreditChinaCompanyListMapper;
import org.crawler.service.spider.creditchina.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * 企业信息爬取结果处理器
 *
 * @author: liushuwei
 * @date: 16:20
 * @version: 1.9
 */
@Component
@Slf4j
public class CreditChinaCompanyListProcessor implements PageProcessor {

    @Autowired
    private CreditChinaCompanyListMapper creditChinaCompanyListMapper;

    /**
     * 站点配置
     *      - chrome 浏览器 user agent
     *      - 超时时间 10 s
     *      - 重试 1 次
     *      - 循环换重试 3 次（将失败的 url，重新放入队列尾部，知道达到重试次数）
     *      - 设置域名
     *      - 设置爬取间隔 200 ms (爬取 1 个休眠 200 ms)
     */
    private Site site = Site.me().setCharset("utf-8")
            .setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/100.0.4896.75 Safari/537.36")
            .setTimeOut(5 * 1000)
            .setCycleRetryTimes(3)
            .setDomain("baidu.com")
            .addHeader("Referer", "https://www.baidu.com")
            .setSleepTime(200);

    @Override
    public Site getSite() {
        return site;
    }

    @Override
    public void process(Page page) {
        // 访问 url 得到数据，进行解析处理
        if (page == null || StringUtils.isBlank(page.getRawText())) {
            log.error("当前 url 无有效数据，直接跳过 {}", page.getUrl());
            return;
        }

        // 判断类型 “首页搜索” | “详情页”
        if (page.getUrl().toString().contains("catalogSearchHome")) {
            log.info("正在解析首页搜索 url : {}", page.getUrl().toString());
            // 首页搜索
            SearchHomeApiResult apiResult = JSON.parseObject(page.getRawText(), SearchHomeApiResult.class);
            if (apiResult.getStatus() != 1 && apiResult == null || apiResult.getData() == null) {
                log.error("无效 api 结果 url : {}", page.getUrl());
            }
            SearchHomeData data  = apiResult.getData();
            List<SearchHomeList> list = data.getList();
            if (CollectionUtils.isEmpty(list)) {
                log.error("无效 api 结果，不存在数据信息， url : {}", page.getUrl());
            }
            for (SearchHomeList searchHomeList: list) {
                // 根据统一信用码，判断是否存在唯一信用码
                String accurateEntityCode = searchHomeList.getAccurate_entity_code();
                if (StringUtils.isBlank(accurateEntityCode)) {
                    log.error("无效公司，不存在统一信用码 searchHomeList : {}", searchHomeList);
                    continue;
                }

                // 去重查询
                if (creditChinaCompanyListMapper != null) {
                    QueryWrapper<CreditChinaCompanyList> queryWrapper = new QueryWrapper<>();
                        queryWrapper.select("accurate_entity_code");
                        queryWrapper.eq("accurate_entity_code", accurateEntityCode);
                        queryWrapper.last("limit 1");
                    Integer queryDOCount = creditChinaCompanyListMapper.selectCount(queryWrapper);
                    if (queryDOCount != null && queryDOCount >= 1) {
                        log.error("该公司已经录入，无需重复导入 searchHomeList : {}", searchHomeList);
                        continue;
                    }
                }

               // 持久化入库
                CreditChinaCompanyList company = new CreditChinaCompanyList();
                    company.setAccurateEntityName(searchHomeList.getAccurate_entity_name());
                    company.setAccurateEntityCode(searchHomeList.getAccurate_entity_code());
                    company.setUuid(searchHomeList.getUuid());
                    company.setRecid(searchHomeList.getRecid());
                    company.setAccurateEntityNameQuery(searchHomeList.getAccurate_entity_name_query());
                    company.setEntityType(searchHomeList.getEntityType());

               // 防止本地测试并未注入组件，所以此处加入 null 判断
               if (creditChinaCompanyListMapper != null) {
                   if (creditChinaCompanyListMapper.insert(company) >= 1) {
                       log.info("[信用中国] 成功插入一条数据 id : {}", company.getId());
                   } else {
                       log.error("[信用中国] 插入失败 company : {}", company);
                   }
               }

               // 构建详情页链接请求
                String keyword = page.getUrl().regex("keyword=([^&]*)").get();
                String detailUrl = buildDetailsContentUrl(keyword, searchHomeList.getUuid());
                page.addTargetRequest(new Request(detailUrl));
            }
        } else if (page.getUrl().toString().contains("getTyshxydmDetailsContent")) {
            log.info("正在解析企业详情页 url : {}", page.getUrl().toString());
            // 详情页
            DetailsContentApiResult apiResult = JSON.parseObject(page.getRawText(), DetailsContentApiResult.class);
            if (apiResult.getStatus() != 1 && apiResult == null || apiResult.getData() == null) {
                log.error("无效 api 结果 url : {}", page.getUrl().toString());
            }
            // 查询是否存在（防止列表无插入，直接更新报错）
            String uuid = page.getUrl().regex("uuid=([^&]*)").get();
            if (creditChinaCompanyListMapper != null) {
                QueryWrapper<CreditChinaCompanyList> queryWrapper = new QueryWrapper<>();
                    queryWrapper.eq("uuid", uuid);
                    queryWrapper.last("limit 1");
                CreditChinaCompanyList queryDO = creditChinaCompanyListMapper.selectOne(queryWrapper);
                if (queryDO == null) {
                    log.error("未查询到对应公司 uuid : {}, url : {}", uuid, page.getUrl().toString());
                    return;
                }

                    DetailsContentData data = apiResult.getData();
                    DetailsContentDataData dataData = data.getData();

                    // 更新公司详情内容
                    CreditChinaCompanyList updateDO = new CreditChinaCompanyList();
                    // id 更新
                    updateDO.setId(queryDO.getId());

                    if (dataData != null) {
                        updateDO.setDateSource(dataData.getDataSource());
                        updateDO.setDataCatalog(dataData.getData_catalog());

                        DetailsContentDataDataEntity entity = dataData.getEntity();
                        if (entity != null) {
                            updateDO.setEntityName(entity.getName());
                            updateDO.setEntityEnttype(entity.getEnttype());
                            updateDO.setEntityDom(entity.getDom());

                            if (entity.getRegorg() != null) {
                                // 注册单位可能为 null
                                updateDO.setEntityRegorg(entity.getRegorg());
                            }
                        }
                    }

                    // 更新时间精确到秒
                    updateDO.setUpdateTime(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));

                if (creditChinaCompanyListMapper.updateById(updateDO) >= 1) {
                    log.info("[信用中国] 更新详情成功 updateId : {}", updateDO.getId());
                } else {
                    log.error("[信用中国] 更新详情失败 updateDO : {}", updateDO);
                }
            }
        }
    }

    /**
     * 构建首页搜索请求
     *
     * @param keyword 关键字
     * @param page 当前页
     * @param pageSize 显示刷领
     * @return String 完整 url
     */
    public static String buildSearchHomeUrl(String keyword, Integer page, Integer pageSize) {
       String apiUrl = "http://public.creditchina.gov.cn/private-api/catalogSearchHome?keyword=" + keyword
               +"&scenes=defaultScenario&tableName=credit_xyzx_tyshxydm&searchState=2&entityType=1,2,4,5,6,7,8&templateId=&page=" + page + "&pageSize=" + pageSize;
       return apiUrl;
    }

    /**
     * 构建详情页搜索请求
     *
     * @param keyword 关键字
     * @param uuid 详情页 uuid
     * @return String 完整 url
     */
    public static String buildDetailsContentUrl(String keyword, String uuid) {
        String apiUrl = "http://public.creditchina.gov.cn/private-api/getTyshxydmDetailsContent?keyword=" + keyword
                + "&scenes=defaultscenario&entityType=1&searchState=1&uuid=" + uuid;
        return apiUrl;
    }



    /**
     * 本地执行函数
     *      - 直接测试值
     */
    public static void main(String[] args) {
        Spider.create(new CreditChinaCompanyListProcessor())
                .addUrl(buildSearchHomeUrl("奈雪", 1, 5))
                .thread(2)
                .run();
    }
}

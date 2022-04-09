package org.crawler.service.spider;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.crawler.entity.CompanyShorter;
import org.crawler.mapper.CompanyShorterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StopWatch;
import us.codecraft.webmagic.*;
import us.codecraft.webmagic.model.HttpRequestBody;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.utils.HttpConstant;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * 公司列表爬虫组件
 *      - 启动时直接执行 order 值越小，优先级越高
 *
 * @author: liushuwei
 * @date: 16:09
 * @version: 1.9
 */
@Component
@Order(value = 10)
@Slf4j
public class CompanyListSpiderComponent implements ApplicationRunner {

    @Autowired
    private CompanyShorterMapper companyShorterMapper;

    @Autowired
    private CompanyListProcessor companyListProcessor;

    @Autowired
    private CompanyListPipeline companyListPipeline;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // spring 项目初始化后执行
        this.start();
    }

    /**
     * 爬虫启动函数
     */
    public void start() {
        // 查询所有总数
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        int total = companyShorterMapper.selectCount(new QueryWrapper<>());

        int pageNum = 1;
        int pageSize = 20;
        int counter = 1;
        while (counter < total) {
            // 初始化爬虫(设置 “处理器” 和 “持久化” 工具)
            Spider spider = Spider.create(companyListProcessor)
                    .addPipeline(companyListPipeline);
            log.info("当前执行 pageNum : {}, pageSize : {}, couter : {}, total : {}", pageNum, pageSize, counter, total);


            Page<CompanyShorter> page = new Page<>(pageNum, pageSize);
            Page<CompanyShorter> pageResult = companyShorterMapper.selectPage(page, new QueryWrapper<>());
            if (!CollectionUtils.isEmpty(pageResult.getRecords())) {
                for (CompanyShorter shorter: pageResult.getRecords()) {
                    String name = shorter.getName();

                    // 添加爬虫请求
                    spider.addRequest(this.buildRequest(name));

                    counter ++;
                }
            }

            // 启动多线程爬虫
            spider.thread(10).run();

            // 执行完毕后，执行下一批爬取
            pageNum ++;
        }

        stopWatch.stop();

        log.info("*********************************爬虫执行完毕，总共用时 {} 秒，共爬取链接 {} 条", stopWatch.getTotalTimeSeconds(), counter);

    }



    /**
     * 构建 webMagic post 请求
     *
     * @param searchCompanyName 公司名称
     * @return Reqquest Post 请求
     */
    public Request buildRequest(String searchCompanyName) {
        Request request = new Request("http://gsxt.gdgs.gov.cn/gsxt_gd/ent/base/list");

        //设置POST请求
        int currentPage = 1;
        int pageSize = 5;

        // 当前企业状态（1 == “存续（在营、开业、在册）”）
        int opState = 1;

        String searchKey = searchCompanyName;
        String bodyJson = "{" +
                "  \"current\": " + currentPage + "," +
                "  \"size\": " + pageSize + ",\n" +
                "  \"entity\": {\n" +
                "    \"keyAreasType\": null,\n" +
                "    \"heightLight\": true,\n" +
                "    \"opState\": \"" + opState + "\",\n" +
                "    \"opYear\": \"\",\n" +
                "    \"regOrg\": \"\",\n" +
                "    \"licence\": \"\",\n" +
                "    \"searchWord\": \"" + searchKey + "\",\n" +
                "    \"searchType\": \"0\"\n" +
                "  }\n" +
                "}";
        request.setRequestBody(HttpRequestBody.json(bodyJson, "utf-8"));

        //只有POST请求才可以添加附加参数
        request.setMethod(HttpConstant.Method.POST);

        return request;
    }
}

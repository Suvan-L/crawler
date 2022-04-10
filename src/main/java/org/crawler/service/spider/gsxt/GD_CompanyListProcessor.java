package org.crawler.service.spider.gsxt;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * 企业信息爬取结果处理器
 *
 * @author: liushuwei
 * @date: 16:20
 * @version: 1.9
 */

@Component
@Slf4j
public class GD_CompanyListProcessor implements PageProcessor {

    /**
     * 爬虫实例
     *      - 3 秒休眠 1 次
     */
    private Site site = Site.me().setRetryTimes(3).setSleepTime(0);

    @Override
    public Site getSite() {
        return site;
    }

    @Override
    public void process(Page page) {
        // 访问 url 得到数据，进行解析处理
        log.info("已爬取数据 url : {}" , page.getUrl());

        String rewText = page.getRawText();

        GD_CompanyListApiResult apiResult = JSON.parseObject(rewText, GD_CompanyListApiResult.class);
        if (!CollectionUtils.isEmpty(apiResult.getData().getRecords())) {
            // 将所有内容进行查询入库
            page.putField("records", apiResult.getData().getRecords());
        }
    }
}

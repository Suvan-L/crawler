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
            .setTimeOut(10 * 1000)
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
        log.info("已爬取数据 url : {}" , page.getUrl());

        String rewText = page.getRawText();

        GD_CompanyListApiResult apiResult = JSON.parseObject(rewText, GD_CompanyListApiResult.class);
        if (!CollectionUtils.isEmpty(apiResult.getData().getRecords())) {
            // 将所有内容进行查询入库
            page.putField("records", apiResult.getData().getRecords());
        }
    }
}

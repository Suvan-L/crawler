package org.crawler.service.spider.creditchina;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.crawler.entity.CompanyShorter;
import org.crawler.mapper.CompanyShorterMapper;
import org.crawler.service.spider.gsxt.GD_CompanyListPipeline;
import org.crawler.service.spider.gsxt.GD_CompanyListProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StopWatch;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.model.HttpRequestBody;
import us.codecraft.webmagic.utils.HttpConstant;

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
public class CreditChinaCompanyListSpiderComponent implements ApplicationRunner {

    @Autowired
    private CompanyShorterMapper companyShorterMapper;

    @Autowired
    private CreditChinaCompanyListProcessor creditChinaCompanyListProcessor;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // spring 项目初始化后执行
        log.info("启动 [信用中国] 数据爬虫 (10 线程) >>>>>>>>>>>>>>>>>>>>>");
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
            Spider spider = Spider.create(creditChinaCompanyListProcessor);
            log.info("[信用中国] [匹配公司简称字典] 当前执行 pageNum : {}, pageSize : {}, couter : {}, total : {}", pageNum, pageSize, counter, total);


            Page<CompanyShorter> page = new Page<>(pageNum, pageSize);
            Page<CompanyShorter> pageResult = companyShorterMapper.selectPage(page, new QueryWrapper<>());
            if (!CollectionUtils.isEmpty(pageResult.getRecords())) {
                for (CompanyShorter shorter: pageResult.getRecords()) {
                    String name = shorter.getName();

                    // 添加爬虫请求(只爬取首页前 5 条)（热点数据）
                    String apiUrl = CreditChinaCompanyListProcessor.buildSearchHomeUrl(name, 1, 5);
                    spider.addUrl(apiUrl);

                    counter ++;
                }
            }

            // 启动多线程爬虫
            spider.thread(10).run();

            // 执行完毕后，执行下一批爬取
            pageNum ++;
        }

        stopWatch.stop();

        log.info("********************************* [信用中国] 爬虫执行完毕，总共用时 {} 秒，共爬取链接 {} 条", stopWatch.getTotalTimeSeconds(), counter);

    }
}

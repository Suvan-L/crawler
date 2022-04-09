package org.crawler.spider;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.crawler.service.spider.CompanyListSpiderComponent;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.CollectionUtils;
import us.codecraft.webmagic.*;
import us.codecraft.webmagic.model.HttpRequestBody;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.utils.HttpConstant;

import java.util.List;

/**
 * 企业信息列表爬虫
 *      - 用于测试 webMagic 框架
 *
 * @author: liushuwei
 * @date: 14:26
 * @version: 1.9
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Slf4j
public class CompanyListSpiderTest {

    @Autowired
    private CompanyListSpiderComponent companyListSpiderComponent;

    /**
     * 执行爬虫
     *      - 本地启动
     */
    @Test
    @Ignore
    public void testStart() {
        companyListSpiderComponent.start();
    }
}
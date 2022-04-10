package org.crawler.service.spider.gsxt;

import lombok.extern.slf4j.Slf4j;
import org.crawler.service.spider.gsxt.GD_CompanyListSpiderComponent;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
public class GD_CompanyListSpiderTest {

    @Autowired
    private GD_CompanyListSpiderComponent GDCompanyListSpiderComponent;

    /**
     * 执行爬虫
     *      - 本地启动
     */
    @Test
    @Ignore
    public void testStart() {
        GDCompanyListSpiderComponent.start();
    }
}
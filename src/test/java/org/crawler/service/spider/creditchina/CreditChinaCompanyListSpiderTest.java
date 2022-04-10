package org.crawler.service.spider.creditchina;

import lombok.extern.slf4j.Slf4j;
import org.crawler.service.spider.gsxt.GD_CompanyListSpiderComponent;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * [信用中国] 爬虫测试
 *
 * @author: liushuwei
 * @date: 2022.04.10
 * @version: 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Slf4j
public class CreditChinaCompanyListSpiderTest {

    @Autowired
    private CreditChinaCompanyListSpiderComponent creditChinaCompanyListSpiderComponent;

    /**
     * 执行爬虫
     *      - 本地启动
     */
    @Test
    @Ignore
    public void testStart() {
        creditChinaCompanyListSpiderComponent.start();
    }
}
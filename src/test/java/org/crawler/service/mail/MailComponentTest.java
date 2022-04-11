package org.crawler.service.mail;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 导入数据
 *
 * @author: liushuwei
 * @date: 12:56
 * @version: 1.9
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Slf4j
public class MailComponentTest {

    @Autowired
    private MailComponent mailComponent;


    /**
     * 测试发送邮件通知
     */
    @Test
    public void testMail() {

        mailComponent.sendSimpleMail("xxxxx@xxxx.com", "【crawler】爬虫进度通知", "[信用中国] 爬虫已执行完毕！");
    }

}

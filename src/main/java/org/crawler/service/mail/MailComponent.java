package org.crawler.service.mail;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

/**
 * 邮件服务组件
 *      - 参考案例：https://blog.51cto.com/u_14149663/3294698
 *
 * @author: liushuwei
 * @date: 1:01
 * @version: 1.9
 */
@Component
@Slf4j
public class MailComponent {

    //指定发送者
    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private JavaMailSender mailSender;


    /*
     * 发送简单文本邮件
     * to：发送给谁
     * subject：发送的主题（邮件主题）
     * content：发送的内容
     * */
    public void sendSimpleMail(String to, String subject, String content){
        // 启动线程，异步发送
        new Thread(new Runnable() {
            @Override
            public void run() {
                SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
                simpleMailMessage.setTo(to);
                simpleMailMessage.setSubject(subject);
                simpleMailMessage.setText(content);
                simpleMailMessage.setFrom(from);

                //发送邮件
                try {
                    mailSender.send(simpleMailMessage);
                    log.info("简单邮件发送成功");
                } catch (MailException e) {
                    log.error("简单邮件发送失败",e);
                }
            }
        }).start();


    }

}

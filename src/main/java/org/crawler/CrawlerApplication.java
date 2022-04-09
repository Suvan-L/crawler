package org.crawler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CrawlerApplication {

    /**
     * 启动爬虫项目
     */
    public static void main(String[] args) {
        SpringApplication.run(CrawlerApplication.class, args);
    }

}

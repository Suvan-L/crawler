package org.crawler.service.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 自动化测试组件
 *
 * @author: liushuwei
 * @date: 14:40
 * @version: 1.9
 */
public class SeleniumComponent {

    /**
     * 执行函数
     *      - 测试 selenium 调用 chrome 浏览器驱动，模拟访问网页
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        // 想要测试，需要本地确保安装 chrome，并与驱动对应版本
        //Chrome 已是最新版本 版本 100.0.4896.75（正式版本） （64 位）
        // 当前驱动版本 https://chromedriver.storage.googleapis.com/index.html?path=101.0.4951.15/

        String projectPath = System.getProperty("user.dir");
        String filePath = "/driver/";

        // 连接 chrome 浏览器
        System.setProperty("webdriver.chrome.driver", projectPath + filePath + "110.0.4951.15_chromedriver_win32.exe");

        // 创建一个 Chrome 浏览器实例
        WebDriverManager.chromedriver().setup();

        WebDriver driver = new ChromeDriver();

        // 浏览器访问链接（国家总局）
        driver.get("https://www.tianyancha.com/search?key=百度");

        // 隐式等待 5 s 再操作，防止浏览器并未加载页面，导致无法操作元素
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);


        // 输出页面标题和源码
        System.out.println("************************************");
        System.out.println("当前 url: " + driver.getCurrentUrl());
        System.out.println("当前标题: " + driver.getTitle());
        System.out.println("当前 headers: " + driver.getWindowHandles());
        System.out.println("****************************************");
        System.out.println("源码: >>>>>>>>>>>>>>>>>>>>>>");
        // 初次访问
        String pageSource = driver.getPageSource();
        System.out.println(pageSource);

        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<:源码");
        System.out.println("***************************************");


        // 关闭浏览器
        driver.quit();
    }
}

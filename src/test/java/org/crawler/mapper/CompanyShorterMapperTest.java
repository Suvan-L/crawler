package org.crawler.mapper;

import lombok.extern.slf4j.Slf4j;
import org.crawler.entity.CompanyShorter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.*;

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
public class CompanyShorterMapperTest {

    @Autowired
    private CompanyShorterMapper companyShorterMapper;


    /**
     * 导入公司简称数据
     *      - 建议直接使用 navicat 工具导入，速度更快，效果更好
     */
    @Test
    public void insertTxtDate() throws IOException, InterruptedException {
        String projectPath = System.getProperty("user.dir");
        String filePath = "/doc/";
        String fileName = "Company-Shorter-Form.txt";

        // 读取文件
        FileInputStream inputStream = new FileInputStream(projectPath + filePath + fileName);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        // 此处保持顺序性，所以不用线程池
        int counter = 1;
        String lineStr = null;
        while ((lineStr = bufferedReader.readLine()) != null) {
            log.info("读取内容 lineStr : {}", lineStr);
            // 逐行插入
            CompanyShorter saveDO = new CompanyShorter();
                // 设置名称
                saveDO.setName(lineStr);

                if (companyShorterMapper.insert(saveDO) >= 1) {
                    log.info("插入成功 companyShorterId : {}", saveDO.getId());
                }

            counter ++;
        }
        log.info("共插入 {} 条公司简称", counter);

        inputStream.close();;
    }

}

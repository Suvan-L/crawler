package org.crawler.service.spider.gsxt;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.crawler.entity.CompanyList;
import org.crawler.mapper.CompanyListMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 企业信息爬取结果持久化处理
 *
 * @author: liushuwei
 * @date: 16:21
 * @version: 1.9
 */
@Slf4j
@Component
public class GD_CompanyListPipeline implements Pipeline {

    @Autowired
    private CompanyListMapper companyListMapper;

    @Override
    public void process(ResultItems resultItems, Task task) {
        // log.info("接收处理参数 resultItems: {}, task : {}", resultItems, task);
        List<GD_CompanyListApiResult.DataDTO.RecordsDTO> recordsDTOList = resultItems.get("records");
        if (recordsDTOList != null) {
            for (GD_CompanyListApiResult.DataDTO.RecordsDTO recordsDTO : recordsDTOList) {
                try {
                    // TODO 先查询是否存在
                    String uni_scid = recordsDTO.getUniSCID();
                    if (StringUtils.isBlank(uni_scid)) {
                        log.error("不存在信用代码，直接跳过 recordsDTO : {}", recordsDTO);
                        continue;
                    }

                    QueryWrapper<CompanyList> queryWrapper = new QueryWrapper<>();
                    queryWrapper.eq("uni_scid", uni_scid);
                    queryWrapper.last("limit 1");
                    CompanyList companyList = companyListMapper.selectOne(queryWrapper);
                    if (companyList != null) {
                        log.error("村子重复，跳过插入操作 companyList : {}", companyList);
                        continue;
                    }


                    CompanyList saveDO = new CompanyList();

                        saveDO.setEntName(this.delHTMLTag(recordsDTO.getEntName()));
                        saveDO.setUniScid(recordsDTO.getUniSCID());
                        saveDO.setRegNo(recordsDTO.getRegNo());
                        saveDO.setLeRep(recordsDTO.getLeRep());

                        // 防止解析失败
                        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                                .appendPattern("yyyy-MM-dd [[hh][:mm][:ss]]")
                                .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                                .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                                .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
                                .parseDefaulting(ChronoField.MILLI_OF_SECOND, 0)
                                .toFormatter();
                        saveDO.setEstDate(LocalDateTime.parse(recordsDTO.getEstDate(), formatter));

                        saveDO.setOpStateCcn(recordsDTO.getOpStateCn());

                        saveDO.setRegCapital(new BigDecimal(recordsDTO.getRegCapital()));
                        saveDO.setReCapitalCoin(recordsDTO.getRegCapitalCoin());
                        if (recordsDTO.getLink() != null) {
                            saveDO.setLink(recordsDTO.getLink().toString());
                        }
                        if (companyListMapper.insert(saveDO) >= 1) {
                            log.info("成功插入 companyList id : {}", saveDO.getId());
                        }
                } catch (Exception e) {
                    // 防止因为单个出现异常，导致一批都失效
                    log.error("出现异常 e : {}", e.getMessage());
                }
            }
        }

        // 进行入库操作
    }


    /**
     * 去除 html 标签
     *
     * @param htmlStr 去除 html 标签
     * @return String 调整后的字符串
     */
    public String delHTMLTag(String htmlStr){
//        String regEx_script="<script[^>]*?>[\\s\\S]*?<\\/script>"; //定义script的正则表达式
//        Pattern p_script=Pattern.compile(regEx_script,Pattern.CASE_INSENSITIVE);
//        Matcher m_script=p_script.matcher(htmlStr);
//        htmlStr=m_script.replaceAll(""); //过滤
//
//        String regEx_style="<style[^>]*?>[\\s\\S]*?<\\/style>"; //定义style的正则表达式
//        Pattern p_style=Pattern.compile(regEx_style,Pattern.CASE_INSENSITIVE);
//        Matcher m_style=p_style.matcher(htmlStr);
//        htmlStr=m_style.replaceAll(""); //过滤style标签

        String regEx_html="<[^>]+>"; //定义HTML标签的正则表达式
        Pattern p_html=Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE);
        Matcher m_html=p_html.matcher(htmlStr);
        htmlStr=m_html.replaceAll(""); //过滤html标签

        return htmlStr.trim(); //返回文本字符串
    }

}


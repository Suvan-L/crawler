package org.crawler.mapper;

import org.crawler.entity.CreditChinaCompanyList;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


/**
* <p>
* （信用中国）企业工商信息列表 Mapper 接口
* </p>
*
* @author liushuwei
* @since 2022-04-10
*/
@Mapper
@Repository
public interface CreditChinaCompanyListMapper extends BaseMapper<CreditChinaCompanyList> {

}

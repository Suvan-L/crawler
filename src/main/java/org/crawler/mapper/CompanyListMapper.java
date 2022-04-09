package org.crawler.mapper;

import org.crawler.entity.CompanyList;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


/**
* <p>
* 企业工商信息列表 Mapper 接口
* </p>
*
* @author liushuwei
* @since 2022-04-09
*/
@Mapper
@Repository
public interface CompanyListMapper extends BaseMapper<CompanyList> {

}

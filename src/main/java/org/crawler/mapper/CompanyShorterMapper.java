package org.crawler.mapper;

import org.crawler.entity.CompanyShorter;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


/**
* <p>
* 公司简称（词库） Mapper 接口
* </p>
*
* @author liushuwei
* @since 2022-04-09
*/
@Mapper
@Repository
public interface CompanyShorterMapper extends BaseMapper<CompanyShorter> {

}

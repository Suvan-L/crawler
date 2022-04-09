package org.crawler.service;

import org.crawler.entity.CompanyShorter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
* <p>
* 公司简称（词库） 服务类
* </p>
*
* @author liushuwei
* @since 2022-04-09
*/
public interface CompanyShorterService {

    /**
    * 分页查询 CompanyShorter
    *
    * @param page     当前页数
    * @param pageSize 页的大小
    * @param factor  搜索关键词
    * @return 返回 mybatis-plus 的 Page 对象,其中 records 字段为符合条件的查询结果
    * @author liushuwei
    * @since 2022-04-09
    */
    Page<CompanyShorter> listsByPage(int page, int pageSize, String factor);

    /**
    * 根据id查询 CompanyShorter
    *
    * @param id 需要查询的 CompanyShorter 的id
    * @return 返回对应id的 CompanyShorter 对象
    * @author liushuwei
    * @since 2022-04-09
    */
    CompanyShorter getById(int id);

    /**
    * 插入 CompanyShorter
    *
    * @param companyShorter 需要插入的 CompanyShorter 对象
    * @return 返回插入成功之后 CompanyShorter 对象的id
    * @author liushuwei
    * @since 2022-04-09
    */
    int insert(CompanyShorter companyShorter);

    /**
    * 根据id删除 CompanyShorter
    *
    * @param id 需要删除的 CompanyShorter 对象的id
    * @return 返回被删除的 CompanyShorter 对象的id
    * @author liushuwei
    * @since 2022-04-09
    */
    int deleteById(int id);

    /**
    * 根据id更新 CompanyShorter
    *
    * @param companyShorter 需要更新的CompanyShorter对象
    * @return 返回被更新的 CompanyShorter 对象的id
    * @author liushuwei
    * @since 2022-04-09
    */
    int update(CompanyShorter companyShorter);

}

package org.crawler.service;

import org.crawler.entity.CompanyList;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
* <p>
* 企业工商信息列表 服务类
* </p>
*
* @author liushuwei
* @since 2022-04-09
*/
public interface CompanyListService {

    /**
    * 分页查询 CompanyList
    *
    * @param page     当前页数
    * @param pageSize 页的大小
    * @param factor  搜索关键词
    * @return 返回 mybatis-plus 的 Page 对象,其中 records 字段为符合条件的查询结果
    * @author liushuwei
    * @since 2022-04-09
    */
    Page<CompanyList> listsByPage(int page, int pageSize, String factor);

    /**
    * 根据id查询 CompanyList
    *
    * @param id 需要查询的 CompanyList 的id
    * @return 返回对应id的 CompanyList 对象
    * @author liushuwei
    * @since 2022-04-09
    */
    CompanyList getById(int id);

    /**
    * 插入 CompanyList
    *
    * @param companyList 需要插入的 CompanyList 对象
    * @return 返回插入成功之后 CompanyList 对象的id
    * @author liushuwei
    * @since 2022-04-09
    */
    int insert(CompanyList companyList);

    /**
    * 根据id删除 CompanyList
    *
    * @param id 需要删除的 CompanyList 对象的id
    * @return 返回被删除的 CompanyList 对象的id
    * @author liushuwei
    * @since 2022-04-09
    */
    int deleteById(int id);

    /**
    * 根据id更新 CompanyList
    *
    * @param companyList 需要更新的CompanyList对象
    * @return 返回被更新的 CompanyList 对象的id
    * @author liushuwei
    * @since 2022-04-09
    */
    int update(CompanyList companyList);

}

package org.crawler.service;

import org.crawler.entity.CreditChinaCompanyList;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
* <p>
* （信用中国）企业工商信息列表 服务类
* </p>
*
* @author liushuwei
* @since 2022-04-10
*/
public interface CreditChinaCompanyListService {

    /**
    * 分页查询 CreditChinaCompanyList
    *
    * @param page     当前页数
    * @param pageSize 页的大小
    * @param factor  搜索关键词
    * @return 返回 mybatis-plus 的 Page 对象,其中 records 字段为符合条件的查询结果
    * @author liushuwei
    * @since 2022-04-10
    */
    Page<CreditChinaCompanyList> listsByPage(int page, int pageSize, String factor);

    /**
    * 根据id查询 CreditChinaCompanyList
    *
    * @param id 需要查询的 CreditChinaCompanyList 的id
    * @return 返回对应id的 CreditChinaCompanyList 对象
    * @author liushuwei
    * @since 2022-04-10
    */
    CreditChinaCompanyList getById(int id);

    /**
    * 插入 CreditChinaCompanyList
    *
    * @param creditChinaCompanyList 需要插入的 CreditChinaCompanyList 对象
    * @return 返回插入成功之后 CreditChinaCompanyList 对象的id
    * @author liushuwei
    * @since 2022-04-10
    */
    int insert(CreditChinaCompanyList creditChinaCompanyList);

    /**
    * 根据id删除 CreditChinaCompanyList
    *
    * @param id 需要删除的 CreditChinaCompanyList 对象的id
    * @return 返回被删除的 CreditChinaCompanyList 对象的id
    * @author liushuwei
    * @since 2022-04-10
    */
    int deleteById(int id);

    /**
    * 根据id更新 CreditChinaCompanyList
    *
    * @param creditChinaCompanyList 需要更新的CreditChinaCompanyList对象
    * @return 返回被更新的 CreditChinaCompanyList 对象的id
    * @author liushuwei
    * @since 2022-04-10
    */
    int update(CreditChinaCompanyList creditChinaCompanyList);

}

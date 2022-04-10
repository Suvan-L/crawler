package org.crawler.controller;


import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.crawler.entity.dto.ResultBean;
import org.crawler.service.CreditChinaCompanyListService;
import org.crawler.entity.CreditChinaCompanyList;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;


/**
 * <p>
 * （信用中国）企业工商信息列表 前端控制器
 * </p>
 *
 * @author liushuwei
 * @since 2022-04-10
 * @version v1.0
 */
@RestController
@RequestMapping("/api/v1/credit-china-company-list")
public class CreditChinaCompanyListController {

    @Resource
    private CreditChinaCompanyListService creditChinaCompanyListService;

    /**
    * 查询分页数据
    */
    @RequestMapping(method = RequestMethod.GET)
    public ResultBean<?> listByPage(@RequestParam(name = "page", defaultValue = "1") int page,
                                    @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
                                    @RequestParam(name = "factor", defaultValue = "") String factor) {
        return new ResultBean<>(creditChinaCompanyListService.listsByPage(page, pageSize,factor));
    }


    /**
    * 根据id查询
    */
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResultBean<?> getById(@PathVariable("id") Integer id) {
        return new ResultBean<>(creditChinaCompanyListService.getById(id));
    }

    /**
    * 新增
    */
    @RequestMapping(method = RequestMethod.POST)
    public ResultBean<?> insert(@RequestBody CreditChinaCompanyList creditChinaCompanyList) {
        return new ResultBean<>(creditChinaCompanyListService.insert(creditChinaCompanyList));
    }

    /**
    * 删除
    */
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResultBean<?> deleteById(@PathVariable("id") Integer id) {
        return new ResultBean<>(creditChinaCompanyListService.deleteById(id));
    }

    /**
    * 修改
    */
    @RequestMapping(method = RequestMethod.PUT)
    public ResultBean<?> updateById(@RequestBody CreditChinaCompanyList creditChinaCompanyList) {
        return new ResultBean<>(creditChinaCompanyListService.update(creditChinaCompanyList));
    }
}

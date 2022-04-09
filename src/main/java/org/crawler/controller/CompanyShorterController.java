package org.crawler.controller;


import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.crawler.entity.dto.ResultBean;
import org.crawler.service.CompanyShorterService;
import org.crawler.entity.CompanyShorter;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;


/**
 * <p>
 * 公司简称（词库） 前端控制器
 * </p>
 *
 * @author liushuwei
 * @since 2022-04-09
 * @version v1.0
 */
@RestController
@RequestMapping("/api/v1/company-shorter")
public class CompanyShorterController {

    @Resource
    private CompanyShorterService companyShorterService;

    /**
    * 查询分页数据
    */
    @RequestMapping(method = RequestMethod.GET)
    public ResultBean<?> listByPage(@RequestParam(name = "page", defaultValue = "1") int page,
                                    @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
                                    @RequestParam(name = "factor", defaultValue = "") String factor) {
        return new ResultBean<>(companyShorterService.listsByPage(page, pageSize,factor));
    }


    /**
    * 根据id查询
    */
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResultBean<?> getById(@PathVariable("id") Integer id) {
        return new ResultBean<>(companyShorterService.getById(id));
    }

    /**
    * 新增
    */
    @RequestMapping(method = RequestMethod.POST)
    public ResultBean<?> insert(@RequestBody CompanyShorter companyShorter) {
        return new ResultBean<>(companyShorterService.insert(companyShorter));
    }

    /**
    * 删除
    */
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResultBean<?> deleteById(@PathVariable("id") Integer id) {
        return new ResultBean<>(companyShorterService.deleteById(id));
    }

    /**
    * 修改
    */
    @RequestMapping(method = RequestMethod.PUT)
    public ResultBean<?> updateById(@RequestBody CompanyShorter companyShorter) {
        return new ResultBean<>(companyShorterService.update(companyShorter));
    }
}

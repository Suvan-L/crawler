package org.crawler.service.impl;

import org.crawler.entity.CompanyList;
import org.crawler.mapper.CompanyListMapper;
import org.crawler.service.CompanyListService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.crawler.exception.bizException.BizException;

/**
* <p>
* 企业工商信息列表 服务实现类
* </p>
*
* @author liushuwei
* @since 2022-04-09
*/
@Slf4j
@Service
public class CompanyListServiceImpl extends ServiceImpl<CompanyListMapper, CompanyList> implements CompanyListService {

    @Override
    public Page<CompanyList> listsByPage(int page, int pageSize, String factor) {
        log.info("正在执行分页查询 companyList : page = {} pageSize = {} factor = {}", page, pageSize, factor);
        QueryWrapper<CompanyList> queryWrapper =  new QueryWrapper<CompanyList>().like("", factor);
        // TODO 这里需要自定义用于匹配的字段,并把 wrapper 传入下面的 page 方法
        Page<CompanyList> result = super.page(new Page<>(page, pageSize));
        log.info("分页查询 companyList 完毕: 结果数 = {} ",result.getRecords().size());
        return result;
    }

    @Override
    public CompanyList getById(int id) {
        log.info("正在查询 companyList 中 id : {} 的数据" , id);
        CompanyList companyList = super.getById(id);
        log.info("查询 id : {} 的 companyList {}",id,(null == companyList?"无结果":"成功"));
        return companyList;
    }

    @Override
    public int insert(CompanyList companyList) {
        log.info("正在插入 companyList ");
        if (super.save(companyList)) {
            log.info("插入companyList成功, id为{}", companyList.getId());
            return companyList.getId();
        } else {
            log.error("插入 companyList 失败");
            throw new BizException("添加失败");
        }
    }

    @Override
    public int deleteById(int id) {
        log.info("正在删除 id:{} 的 companyList" , id);
        if (super.removeById(id)) {
            log.info("删除 id:{} 的 companyList成功", id);
            return id;
        } else {
            log.error("删除 id:{} 的 companyList失败", id);
            throw new BizException("删除失败 [id=" + id + "]");
        }
    }

    @Override
    public int update(CompanyList companyList) {
        log.info("正在更新 id:{} 的 companyList", companyList.getId());
        if (super.updateById(companyList)) {
            log.info("更新d为{}的companyList成功",companyList.getId());
            return companyList.getId();
        } else {
            log.error("更新 id:{} 的 companyList 失败", companyList.getId());
            throw new BizException("更新失败[id=" + companyList.getId() + "]");
        }
    }

}

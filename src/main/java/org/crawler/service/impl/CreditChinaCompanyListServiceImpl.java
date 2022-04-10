package org.crawler.service.impl;

import org.crawler.entity.CreditChinaCompanyList;
import org.crawler.mapper.CreditChinaCompanyListMapper;
import org.crawler.service.CreditChinaCompanyListService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.crawler.exception.bizException.BizException;

/**
* <p>
* （信用中国）企业工商信息列表 服务实现类
* </p>
*
* @author liushuwei
* @since 2022-04-10
*/
@Slf4j
@Service
public class CreditChinaCompanyListServiceImpl extends ServiceImpl<CreditChinaCompanyListMapper, CreditChinaCompanyList> implements CreditChinaCompanyListService {

    @Override
    public Page<CreditChinaCompanyList> listsByPage(int page, int pageSize, String factor) {
        log.info("正在执行分页查询 creditChinaCompanyList : page = {} pageSize = {} factor = {}", page, pageSize, factor);
        QueryWrapper<CreditChinaCompanyList> queryWrapper =  new QueryWrapper<CreditChinaCompanyList>().like("", factor);
        // TODO 这里需要自定义用于匹配的字段,并把 wrapper 传入下面的 page 方法
        Page<CreditChinaCompanyList> result = super.page(new Page<>(page, pageSize));
        log.info("分页查询 creditChinaCompanyList 完毕: 结果数 = {} ",result.getRecords().size());
        return result;
    }

    @Override
    public CreditChinaCompanyList getById(int id) {
        log.info("正在查询 creditChinaCompanyList 中 id : {} 的数据" , id);
        CreditChinaCompanyList creditChinaCompanyList = super.getById(id);
        log.info("查询 id : {} 的 creditChinaCompanyList {}",id,(null == creditChinaCompanyList?"无结果":"成功"));
        return creditChinaCompanyList;
    }

    @Override
    public int insert(CreditChinaCompanyList creditChinaCompanyList) {
        log.info("正在插入 creditChinaCompanyList ");
        if (super.save(creditChinaCompanyList)) {
            log.info("插入creditChinaCompanyList成功, id为{}", creditChinaCompanyList.getId());
            return creditChinaCompanyList.getId();
        } else {
            log.error("插入 creditChinaCompanyList 失败");
            throw new BizException("添加失败");
        }
    }

    @Override
    public int deleteById(int id) {
        log.info("正在删除 id:{} 的 creditChinaCompanyList" , id);
        if (super.removeById(id)) {
            log.info("删除 id:{} 的 creditChinaCompanyList成功", id);
            return id;
        } else {
            log.error("删除 id:{} 的 creditChinaCompanyList失败", id);
            throw new BizException("删除失败 [id=" + id + "]");
        }
    }

    @Override
    public int update(CreditChinaCompanyList creditChinaCompanyList) {
        log.info("正在更新 id:{} 的 creditChinaCompanyList", creditChinaCompanyList.getId());
        if (super.updateById(creditChinaCompanyList)) {
            log.info("更新d为{}的creditChinaCompanyList成功",creditChinaCompanyList.getId());
            return creditChinaCompanyList.getId();
        } else {
            log.error("更新 id:{} 的 creditChinaCompanyList 失败", creditChinaCompanyList.getId());
            throw new BizException("更新失败[id=" + creditChinaCompanyList.getId() + "]");
        }
    }

}

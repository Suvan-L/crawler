package org.crawler.service.impl;

import org.crawler.entity.CompanyShorter;
import org.crawler.mapper.CompanyShorterMapper;
import org.crawler.service.CompanyShorterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.crawler.exception.bizException.BizException;

/**
* <p>
* 公司简称（词库） 服务实现类
* </p>
*
* @author liushuwei
* @since 2022-04-09
*/
@Slf4j
@Service
public class CompanyShorterServiceImpl extends ServiceImpl<CompanyShorterMapper, CompanyShorter> implements CompanyShorterService {

    @Override
    public Page<CompanyShorter> listsByPage(int page, int pageSize, String factor) {
        log.info("正在执行分页查询 companyShorter : page = {} pageSize = {} factor = {}", page, pageSize, factor);
        QueryWrapper<CompanyShorter> queryWrapper =  new QueryWrapper<CompanyShorter>().like("", factor);
        // TODO 这里需要自定义用于匹配的字段,并把 wrapper 传入下面的 page 方法
        Page<CompanyShorter> result = super.page(new Page<>(page, pageSize));
        log.info("分页查询 companyShorter 完毕: 结果数 = {} ",result.getRecords().size());
        return result;
    }

    @Override
    public CompanyShorter getById(int id) {
        log.info("正在查询 companyShorter 中 id : {} 的数据" , id);
        CompanyShorter companyShorter = super.getById(id);
        log.info("查询 id : {} 的 companyShorter {}",id,(null == companyShorter?"无结果":"成功"));
        return companyShorter;
    }

    @Override
    public int insert(CompanyShorter companyShorter) {
        log.info("正在插入 companyShorter ");
        if (super.save(companyShorter)) {
            log.info("插入companyShorter成功, id为{}", companyShorter.getId());
            return companyShorter.getId();
        } else {
            log.error("插入 companyShorter 失败");
            throw new BizException("添加失败");
        }
    }

    @Override
    public int deleteById(int id) {
        log.info("正在删除 id:{} 的 companyShorter" , id);
        if (super.removeById(id)) {
            log.info("删除 id:{} 的 companyShorter成功", id);
            return id;
        } else {
            log.error("删除 id:{} 的 companyShorter失败", id);
            throw new BizException("删除失败 [id=" + id + "]");
        }
    }

    @Override
    public int update(CompanyShorter companyShorter) {
        log.info("正在更新 id:{} 的 companyShorter", companyShorter.getId());
        if (super.updateById(companyShorter)) {
            log.info("更新d为{}的companyShorter成功",companyShorter.getId());
            return companyShorter.getId();
        } else {
            log.error("更新 id:{} 的 companyShorter 失败", companyShorter.getId());
            throw new BizException("更新失败[id=" + companyShorter.getId() + "]");
        }
    }

}

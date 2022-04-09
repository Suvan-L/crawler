package org.crawler.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 企业工商信息列表
 * </p>
 *
 * @author liushuwei
 * @since 2022-04-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("crawler_company_list")
public class CompanyList extends Model<CompanyList> {

    private static final long serialVersionUID = 1L;

    /**
     * id（自增主键）
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * [必填] 公司名
     */
    private String entName;

    /**
     * [必填] 统一社会信用代码
     */
    private String uniScid;

    /**
     * [选填] 注册号（可能为 null）
     */
    private String regNo;

    /**
     * [必填] 法定代表人
     */
    private String leRep;

    /**
     * [必填] 成立日期
     */
    private LocalDateTime estDate;

    /**
     * [必填] 企业状态
     */
    private String opStateCcn;

    /**
     * [必填] 注册资金（2 位小数点）（万元为单位）
     */
    private BigDecimal regCapital;

    /**
     * [必填] 注册资金单位(例：人名币元)
     */
    private String reCapitalCoin;

    /**
     * [选填] 公司详情页 url
     */
    private String link;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}

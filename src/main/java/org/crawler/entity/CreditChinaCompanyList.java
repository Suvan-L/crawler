package org.crawler.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * （信用中国）企业工商信息列表
 * </p>
 *
 * @author liushuwei
 * @since 2022-04-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("crawler_credit_china_company_list")
public class CreditChinaCompanyList extends Model<CreditChinaCompanyList> {

    private static final long serialVersionUID = 1L;

    /**
     * id（自增主键）
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * [必填] 公司名
     */
    private String accurateEntityName;

    /**
     * [必填] 统一社会信用代码
     */
    private String accurateEntityCode;

    /**
     * [选填] 注册号
     */
    private String uuid;

    /**
     * [选填] 法定代表人
     */
    private String recid;

    /**
     * [选填] 公司名（搜索）
     */
    private String accurateEntityNameQuery;

    /**
     * [选填] 主体类型（1-企业法人，5-社团法人，7-事业单位）
     */
    @TableField("entityType")
    private String entityType;

    /**
     * [选填] 数据来源
     */
    private String dateSource;

    /**
     * [选填] 企业状态（例：工商存续（企业））
     */
    private String dataCatalog;

    /**
     * [选填] 法定代表人
     */
    private String entityName;

    /**
     * [选填] 企业类型
     */
    private String entityEnttype;

    /**
     * [选填] 注册日期
     */
    private String entityEsdate;

    /**
     * [选填] 企业地址
     */
    private String entityDom;

    /**
     * [选填] 注册单位
     */
    private String entityRegorg;

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

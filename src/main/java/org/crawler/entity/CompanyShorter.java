package org.crawler.entity;

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
 * 公司简称（词库）
 * </p>
 *
 * @author liushuwei
 * @since 2022-04-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("crawler_company_shorter")
public class CompanyShorter extends Model<CompanyShorter> {

    private static final long serialVersionUID = 1L;

    /**
     * id（自增主键）
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * [必填] 公司简称
     */
    private String name;

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

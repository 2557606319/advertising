package com.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 文章金币任务表 
 * </p>
 *
 * @author joey
 * @since 2020-03-25
 */
@Data
@TableName("tb_article_task")
public class ArticleTask extends Model<ArticleTask> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 发起用户id
     */
    @TableField("user_id")
    private Long userId;
    /**
     * 阅读奖励金币
     */
    private Integer award;
    /**
     * 总奖励金币
     */
    @TableField("sum_award")
    private Long sumAward;
    /**
     * 到期时间
     */
    private String expire;
    private String ctime;
    /**
     * 推广人数
     */
    @TableField("generalize_count")
    private Integer generalizeCount;
    /**
     * 浏览人数
     */
    @TableField("look_count")
    private Integer lookCount;
    /**
     * 广告id
     */
    @TableField("advertising_id")
    private Long advertisingId;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}

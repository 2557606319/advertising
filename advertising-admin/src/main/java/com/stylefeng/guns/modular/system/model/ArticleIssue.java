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
 * 文章发布表
 * </p>
 *
 * @author joey
 * @since 2020-03-25
 */
@Data
@TableName("tb_article_issue")
public class ArticleIssue extends Model<ArticleIssue> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 发布用户
     */
    @TableField("user_id")
    private Long userId;
    /**
     * 文章素材id
     */
    @TableField("article_id")
    private Long articleId;
    /**
     * 评论数
     */
    @TableField("comment_count")
    private Integer commentCount;
    /**
     * 阅读数
     */
    @TableField("look_count")
    private Integer lookCount;
    /**
     * 喜欢数
     */
    @TableField("like_count")
    private Integer likeCount;
    /**
     * 展示广告id
     */
    @TableField("advertising_id")
    private Long advertisingId;

    private Date ctime;

    /**
     * 关联的广告任务id
     */
    @TableField("task_id")
    private Long taskId;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}

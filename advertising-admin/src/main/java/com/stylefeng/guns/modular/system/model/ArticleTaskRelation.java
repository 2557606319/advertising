package com.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 文章金币任务与文章发布表关联表
 * </p>
 *
 * @author joey
 * @since 2020-03-25
 */
@TableName("tb_article_task_relation")
public class ArticleTaskRelation extends Model<ArticleTaskRelation> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 任务id
     */
    @TableField("task_id")
    private Long taskId;
    /**
     * 关联id
     */
    @TableField("relation_id")
    private Long relationId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Long getRelationId() {
        return relationId;
    }

    public void setRelationId(Long relationId) {
        this.relationId = relationId;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "ArticleTaskRelation{" +
        "id=" + id +
        ", taskId=" + taskId +
        ", relationId=" + relationId +
        "}";
    }
}

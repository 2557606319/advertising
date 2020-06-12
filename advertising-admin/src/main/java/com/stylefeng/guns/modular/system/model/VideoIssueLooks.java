package com.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 发布商品浏览记录表
 * </p>
 *
 * @author joey
 * @since 2020-03-25
 */
@TableName("tb_video_issue_looks")
public class VideoIssueLooks extends Model<VideoIssueLooks> {

    private static final long serialVersionUID = 1L;

    /**
     *  
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 发布id
     */
    @TableField("issue_id")
    private Long issueId;
    /**
     * 任务分享id
     */
    @TableField("task_relation_id")
    private Long taskRelationId;
    /**
     * 浏览用户id
     */
    @TableField("look_user_id")
    private Long lookUserId;
    /**
     * 浏览时长
     */
    @TableField("look_time")
    private Integer lookTime;
    /**
     * 是否点击广告 0-未点击 1-已点击
     */
    @TableField("is_click")
    private Integer isClick;
    /**
     * 是否复制商品链接
     */
    @TableField("is_copy")
    private Integer isCopy;
    /**
     * 是否点击联系方式
     */
    @TableField("is_relation")
    private Integer isRelation;
    private Date ctime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIssueId() {
        return issueId;
    }

    public void setIssueId(Long issueId) {
        this.issueId = issueId;
    }

    public Long getTaskRelationId() {
        return taskRelationId;
    }

    public void setTaskRelationId(Long taskRelationId) {
        this.taskRelationId = taskRelationId;
    }

    public Long getLookUserId() {
        return lookUserId;
    }

    public void setLookUserId(Long lookUserId) {
        this.lookUserId = lookUserId;
    }

    public Integer getLookTime() {
        return lookTime;
    }

    public void setLookTime(Integer lookTime) {
        this.lookTime = lookTime;
    }

    public Integer getIsClick() {
        return isClick;
    }

    public void setIsClick(Integer isClick) {
        this.isClick = isClick;
    }

    public Integer getIsCopy() {
        return isCopy;
    }

    public void setIsCopy(Integer isCopy) {
        this.isCopy = isCopy;
    }

    public Integer getIsRelation() {
        return isRelation;
    }

    public void setIsRelation(Integer isRelation) {
        this.isRelation = isRelation;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "VideoIssueLooks{" +
        "id=" + id +
        ", issueId=" + issueId +
        ", taskRelationId=" + taskRelationId +
        ", lookUserId=" + lookUserId +
        ", lookTime=" + lookTime +
        ", isClick=" + isClick +
        ", isCopy=" + isCopy +
        ", isRelation=" + isRelation +
        ", ctime=" + ctime +
        "}";
    }
}

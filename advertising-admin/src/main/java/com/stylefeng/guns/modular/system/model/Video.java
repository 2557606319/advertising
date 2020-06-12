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
 * 视频素材表
 * </p>
 *
 * @author joey
 * @since 2020-03-25
 */
@Data
@TableName("tb_video")
public class Video extends Model<Video> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 创建用户
     */
    @TableField("user_id")
    private Long userId;
    private Date ctime;
    private String title;

    @TableField("type_id")
    private Integer typeId;
    private String describe;
    /**
     * 素材总浏览量
     */
    @TableField("look_count")
    private Long lookCount;

    /**
     * 评论总量
     */
    @TableField("comment_count")
    private Long commentCount;

    /**
     * 喜欢总量
     */
    @TableField("like_count")
    private Long likeCount;


    /**
     * 预览图
     */
    private String previews;
    /**
     * 视频地址
     */
    private String url;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }


}

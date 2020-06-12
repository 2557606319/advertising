package com.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 视频收藏表
 * </p>
 *
 * @author joey
 * @since 2020-03-25
 */
@TableName("tb_video_like")
@Data
public class VideoLike extends Model<VideoLike> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("relation_id")
    private Long relationId;

    private String ctime;

    private Long uid;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }


}

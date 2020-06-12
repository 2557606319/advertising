package com.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 文章素材评论表
 * </p>
 *
 * @author joey
 * @since 2020-03-25
 */
@TableName("tb_article_comment")
@Data
public class ArticleComment extends Model<ArticleComment> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("relation_id")
    private Long relationId;

    private String content;

    private String ctime;

    private Long pid;

    private Long uid;

    private String avatar;

    private String nick;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }


}

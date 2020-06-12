package com.stylefeng.guns.modular.api.dto;


import com.stylefeng.guns.modular.system.model.BaseEntity;
import lombok.Data;

/**
 * 文章素材数据传输dto
 */
@Data
public class ArticleDto extends BaseEntity {
    private Long id;
    private Integer typeId;//文章素材类型
    private String url;//文章链接
    private Long lookCount;//阅读量
    private Long looking;//在看
    private String title;//标题
    private String previews;//封面
    private Long commentCount;
    private Long advId;//广告id
}

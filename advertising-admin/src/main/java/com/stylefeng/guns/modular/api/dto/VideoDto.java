package com.stylefeng.guns.modular.api.dto;


import com.stylefeng.guns.modular.system.model.BaseEntity;
import lombok.Data;

/**
 * 视频素材数据传输dto
 */
@Data
public class VideoDto extends BaseEntity {
    private Long id;
    private Integer typeId;//视频素材类型
    private String url;//视频链接
    private String title;//标题
    private String previews;//封面
    private Long likeCount;
    private Long commentCount;
    private Long advId;//广告id

}

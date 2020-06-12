package com.stylefeng.guns.modular.system.dao;

import com.stylefeng.guns.modular.api.dto.ArticleDto;
import com.stylefeng.guns.modular.api.dto.VideoDto;
import com.stylefeng.guns.modular.system.model.Article;
import com.stylefeng.guns.modular.system.model.Video;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 视频素材表 Mapper 接口
 * </p>
 *
 * @author joey
 * @since 2020-03-25
 */
public interface VideoMapper extends BaseMapper<Video> {

    /**
     * 根据dto条件 查询list
     * @param videoDto
     * @return
     */
    List<Video> list(VideoDto videoDto);

    /**
     * 通过短视频url 链接检查视频是否存在
     * @param url
     * @return
     */
    List<Video> findVideoByUrl(String url);

}

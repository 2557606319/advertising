package com.stylefeng.guns.modular.system.dao;

import com.stylefeng.guns.modular.api.dto.ArticleDto;
import com.stylefeng.guns.modular.api.dto.VideoDto;
import com.stylefeng.guns.modular.system.model.ArticleTask;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 文章金币任务表  Mapper 接口
 * </p>
 *
 * @author joey
 * @since 2020-03-25
 */
public interface ArticleTaskMapper extends BaseMapper<ArticleTask> {

    List<ArticleDto> taskArticleList(ArticleDto articleDto);


}

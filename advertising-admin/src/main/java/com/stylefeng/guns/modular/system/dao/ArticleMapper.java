package com.stylefeng.guns.modular.system.dao;

import com.stylefeng.guns.modular.api.dto.ArticleDto;
import com.stylefeng.guns.modular.system.model.Article;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 文章素材表 Mapper 接口
 * </p>
 *
 * @author joey
 * @since 2020-03-25
 */
public interface ArticleMapper extends BaseMapper<Article> {

    /**
     * 根据dto条件 查询list
     * @param articleDto
     * @return
     */
    List<Article> list(ArticleDto articleDto);

    /**
     * 通过文章url 链接检查文章是否存在
     * @param url
     * @return
     */
    Article articleNumByUrl(String url);

}

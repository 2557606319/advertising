package com.stylefeng.guns.modular.system.service.impl;

import com.stylefeng.guns.modular.system.model.Article;
import com.stylefeng.guns.modular.system.dao.ArticleMapper;
import com.stylefeng.guns.modular.system.service.IArticleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.modular.system.service.processor.ArticleProcessor;
import com.stylefeng.guns.modular.system.service.processor.ArticleProcessorFactory;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 文章素材表 服务实现类
 * </p>
 *
 * @author joey
 * @since 2020-03-25
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {

}

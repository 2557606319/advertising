package com.stylefeng.guns.modular.system.service.processor;

import com.stylefeng.guns.core.util.ArticleMaterialEnum;
import org.jsoup.nodes.Document;

public class ArticleProcessorFactory {
      public static ArticleProcessor newInstance(String targetUrl,Document document){
          if(targetUrl.indexOf(ArticleMaterialEnum.BAIJIAHAO.getMessage())!=-1)
              return new ArticleBaiJiaHaoProcessor(document,targetUrl);

          if(targetUrl.indexOf(ArticleMaterialEnum.WEIXIN.getMessage())!=-1)
              return new ArticleWeiXinProcessor(document,targetUrl);

          if(targetUrl.indexOf(ArticleMaterialEnum.TOUTIAO.getMessage())!=-1||
             targetUrl.indexOf(ArticleMaterialEnum.TOUTIAO_CDN.getMessage())!=-1||
             targetUrl.indexOf(ArticleMaterialEnum.TOUTIAO_1.getMessage())!=-1)
              return new ArticleTouTiaoProcessor(document,targetUrl);

          if(targetUrl.indexOf(ArticleMaterialEnum.SOHU.getMessage())!=-1)
              return new ArticleSoHuProcessor(document,targetUrl);

          return null;
      }
}

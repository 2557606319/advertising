package com.stylefeng.guns.modular.system.service.processor;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * 今日头条文章素材处理器
 */
public class ArticleTouTiaoProcessor extends ArticleProcessor{

    //百家号适配样式
    final static String  bjhStyle="<style> " +
            " body{width: 100%;padding: 20px 15px;min-width:100%} " +
            " body .container{width: 100%;margin-top:0px} " +
            " body .container .index-middle{width: 100%;} " +
            " body .article-box .article-title{font-size: 22px;font-weight: 500;line-height: 1.4;} " +
            " body .index-left{display:none} " +
            " body .toutiao-header{display:none} " +
            " body .detail-comment{display:none} " +
            " body .detail-feed{display:none} " +
            " body .article-tag{display:none} " +
            " body .container .index-right{display:none} " +

            " </style>";

    ArticleTouTiaoProcessor(Document document,String targetUrl){
        super(document,targetUrl);
    }

    @Override
    public String getTitle() {
        Elements elements= super.document.getElementsByClass("article-title");
        //return elements.get(0).text();
        int sidx=super.docStr.indexOf("title:");
        int sidx1 = super.docStr.indexOf("'&quot;",sidx);
        int eidx = super.docStr.indexOf("&quot;'",sidx1);
        try{
            return super.docStr.substring(sidx1+7,eidx);
        }catch (Exception e){
            return "";
        }
    }

    @Override
    public void disposeStyle() {
        //动态生成的此方法不适用
//        super.document.getElementsByClass("index-left").remove();
//        super.document.getElementsByClass("toutiao-header").remove();
//        super.document.getElementsByClass("article-tag").remove();
//        super.document.getElementsByClass("detail-comment").remove();
//        super.document.getElementsByClass("detail-feed").remove();


        Elements es =   super.document.getElementsByTag("body");
        es.prepend(bjhStyle);
    }

    @Override
    public void disposeImgResource() {
        int sidx=super.docStr.indexOf("coverImg:");
        int sidx1 = super.docStr.indexOf("'",sidx);
        int eidx = super.docStr.indexOf("'",sidx1+1);
        try{
            super.firstThreeImgUrl=docStr.substring(sidx1+1,eidx);
        }catch (Exception e){
        }
    }

    @Override
    public long lookCount() {
        String title=getTitle();
        return title.length()*100+title.length();
    }

    @Override
    public Integer type() {
        return null;
    }


}

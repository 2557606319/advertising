package com.stylefeng.guns.modular.system.service.processor;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 百家号文章素材处理器
 */
public class ArticleBaiJiaHaoProcessor extends ArticleProcessor{

    //百家号适配样式
    final static String  bjhStyle="<style> " +
            " body .line-shadow{display: none;} " +
            " body .header_wrap{display: none;}" +
            " body .title_border{margin-top:0px;width:100%;background-color:#fff;border:none}" +
            " body .article-title h2{font-size:22px;line-height:1.4;padding-top:0px;font-weight: 400;}" +
            " body .anci_header_content{width:auto;padding-bottom:22px}" +
            " body .right-container{display: none;}" +
            " body .content-container{width:auto;}" +
            " body .article{width:100%;padding-top:0px}" +
            " body .left-container{width:100%;}" +
            " body .content-container{width:100%;}" +
            " body .anci_header_content .article-desc{margin-right:0px}" +
            " body ::-webkit-scrollbar {width:6px; background-color: rgba(0,0,0,0,0);}" +
            " body ::-webkit-scrollbar-track {border-radius:10px;} " +
            " body ::-webkit-scrollbar-thumb {border-radius:10px; background:#b7b7b7;} " +
            "body .search-diversion{display:none}" +
            "body .bottom-container{display:none}" +
            " body{padding:20px 15px} " +
            " </style>";

    ArticleBaiJiaHaoProcessor(Document document,String targetUrl){
        super(document,targetUrl);
    }

    @Override
    public String getTitle() {
        Elements elements= super.document.getElementsByClass("article-title");
        return elements.get(0).text();
    }

    @Override
    public void disposeStyle() {
        Elements es =   super.document.getElementsByTag("body");
        es.prepend(bjhStyle);
    }

    @Override
    public void disposeImgResource() {
        Elements elements= super.document.getElementById("content-container").getElementsByTag("img");
        if(elements.size()>=0)
            super.firstThreeImgUrl=elements.get(0).attr("src");
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

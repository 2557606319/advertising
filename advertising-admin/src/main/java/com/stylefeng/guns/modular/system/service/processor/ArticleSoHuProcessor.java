package com.stylefeng.guns.modular.system.service.processor;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * 搜狐文章素材处理器
 */
public class ArticleSoHuProcessor extends ArticleProcessor{
    //百家号适配样式
    final static String  bjhStyle="<style> " +
            " body{padding:20px 15px} " +
            " body .area{width:100%} " +
            " .article-page #article-container{padding:0px} " +
            " .article-page #article-container .main{margin-left:0px;width:100%} " +
            " .article-page .text h1{font-size:22px;font-weight:500;line-height:1.4} " +
            " </style>";

    final static String style1="<style>" +
            " .at-title{font-size:22px;font-weight:500;line-height:1.4}" +
            " [data-dpr=\"1\"] .article-page .title-info{font-size:22px;font-weight:500;line-height:1.4}" +
            " [data-dpr=\"1\"] .article-page{padding-top:0px}" +
            " .hide{display:block}" +
            " .lookall-box{display:none}" +
            " .footer-tag-state{display:none}" +
            " .subscribe-container{display:none}" +
            " .article-page .article-main{border-bottom:none}" +
            " #backTop{display:none}" +
            " .article-body, .article-page{background-color:#ffffff}" +
            " .article-page .author-info{background-color:#ffffff}" +

            " </style>";


    ArticleSoHuProcessor(Document document,String targetUrl){
        super(document,targetUrl);
    }

    @Override
    public String getTitle() {
        if(super.targetUrl.indexOf("www.sohu.com")!=-1){
            //www.souhu.com获取标题方式
            Elements elements= super.document.getElementsByClass("text-title");
            return elements.get(0).getElementsByTag("h1").get(0).text();
        }else{
            //3g.k.sohu.com/获取标题方式
            Elements elements= super.document.getElementsByClass("at-title");
            if (elements.size()==0)
                elements=super.document.getElementsByClass("title-info");//m.souhu.com获取标题方式

            return elements.get(0).text();
        }
    }

    @Override
    public void disposeStyle() {


        if(super.targetUrl.indexOf("www.sohu.com")!=-1){
            super.document.getElementsByTag("script").remove();
            super.document.getElementById("main-header").remove();
            super.document.getElementById("backsohucom").remove();
            super.document.getElementById("articleAllsee").remove();
            super.document.getElementById("god_bottom_banner").remove();
            super.document.getElementById("comment_area").remove();
            super.document.getElementsByClass("groom-read").remove();
            super.document.getElementById("right-side-bar").remove();

            super.document.getElementsByClass("location-without-nav").remove();
            super.document.getElementsByClass("column").remove();
            super.document.getElementsByClass("bottom-relate-wrap").remove();

            Elements es = super.document.getElementsByTag("body");
            es.append(bjhStyle);
        }else{
            super.document.getElementsByClass("self-section-stock").remove();
            super.document.getElementsByClass("ui-comment-mod").remove();
            super.document.getElementsByClass("ui-recommend-mod").remove();
            super.document.getElementsByClass("ui-delivery").remove();
            super.document.getElementsByClass("btn-follow").remove();

            if(super.targetUrl.indexOf("3g.k.sohu.com")!=-1){

                Elements hideEl = super.document.getElementsByTag("split");
                Elements elements1 = super.document.getElementsByClass("at-cnt-main");
                elements1.append(hideEl.get(0).html());
                Elements scripts= super.document.getElementsByTag("script");
                scripts.forEach((e)->{
                   String scriptUrl =  e.attr("src");
                   if(scriptUrl.indexOf("tMain.js")!=-1)
                       scripts.remove();
                });
            }else{
                super.document.getElementById("topHeader").remove();
                super.document.getElementById("followAuthor").remove();
                super.document.getElementById("artRecNews").remove();
                super.document.getElementById("artCommentArea").remove();
                super.document.getElementById("middleBanner").remove();
                super.document.getElementsByClass("art-sogou-hotw").remove();
                super.document.getElementsByClass("nav_maps").remove();
                super.document.getElementsByClass("footer_menu").remove();
                super.document.getElementsByClass("bottom-slot").remove();
                super.document.getElementsByClass("footer-tag-state").remove();
            }

            Elements es = super.document.getElementsByTag("body");
            es.append(style1);
        }



//        super.document.getElementsByClass("article-tag").remove();
//        super.document.getElementsByClass("detail-comment").remove();
//        super.document.getElementsByClass("detail-feed").remove();
    }

    @Override
    public void disposeImgResource() {
        if(super.targetUrl.indexOf("www.sohu.com")!=-1) {
            Elements elements= super.document.getElementsByTag("article").get(0).getElementsByTag("img");
            if(elements.size()>0)
                super.firstThreeImgUrl=elements.get(0).attr("src");
        }else{
            Elements elements= super.document.getElementsByClass("at-content");
            if(elements.size()==0)
                elements= super.document.getElementsByClass("article-main");

            Elements imgEle= elements.get(0).getElementsByTag("img");
            if(imgEle.size()!=0)
                super.firstThreeImgUrl=imgEle.get(0).attr("src");

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

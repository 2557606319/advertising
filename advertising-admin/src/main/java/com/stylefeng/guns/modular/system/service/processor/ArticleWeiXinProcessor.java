package com.stylefeng.guns.modular.system.service.processor;

import com.stylefeng.guns.core.util.DateUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.UUID;


/**
 * 微信文章素材处理器
 */
public class ArticleWeiXinProcessor extends ArticleProcessor{

    ArticleWeiXinProcessor(Document document,String targetUrl){
        super(document,targetUrl);
    }

    @Override
    public String getTitle() {
        Element element= super.document.getElementById("activity-name");
        return element.text();
    }

    @Override
    public void disposeStyle() {

    }

    @Override
    public void disposeImgResource() {

        //封面图
        String coverUrl = document.select("meta[property=\"og:image\"]").get(0).attr("content");
        //按照日期分文件夹
        String folder = DateUtil.getDays();
        String fileType ="jpg";
        if(coverUrl.lastIndexOf("=")!=-1)
            fileType= coverUrl.substring(coverUrl.lastIndexOf("=")+1);

        StringBuffer stringBuffer=new StringBuffer("/").append(folder).append("/").
                append(UUID.randomUUID()).append(".").append(fileType);
        String dowImgUrl = stringBuffer.toString();

        super.downloadImage(coverUrl,dowImgUrl);
        super.firstThreeImgUrl=dowImgUrl;

        Elements elements= super.document.getElementsByTag("img");

        //置换微信文章里面的图片为下载后的图片地址
        elements.forEach(item->{
            String beforeUrl=  item.attr("data-src");
            if(beforeUrl.indexOf("http")!=-1) {
                String fileType1="jpg";
                if(beforeUrl.lastIndexOf("=")!=-1)
                    fileType1= beforeUrl.substring(beforeUrl.lastIndexOf("=")+1);

                    StringBuffer sb1 = new StringBuffer("/").append(folder).append("/").
                            append(UUID.randomUUID()).append(".").append(fileType1);
                    item.attr("src","../web-imgs"+sb1);
                    super.downloadImage(beforeUrl,sb1.toString());

            }

        });


    }

    @Override
    public long lookCount() {
        String title=getTitle();
        return title.length()*100+title.length();    }

    @Override
    public Integer type() {
        return null;
    }
}

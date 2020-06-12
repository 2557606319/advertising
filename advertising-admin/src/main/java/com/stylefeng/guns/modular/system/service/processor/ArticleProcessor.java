package com.stylefeng.guns.modular.system.service.processor;

import com.stylefeng.guns.config.properties.GunsProperties;
import com.stylefeng.guns.core.util.HttpUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;

import java.io.File;
import java.util.Base64;

@Getter
@Setter
@Slf4j
public abstract class ArticleProcessor {

    protected Document document;
    protected String docStr;
    protected String firstThreeImgUrl="";//前三张图片的url
    protected String targetUrl;

    final Base64.Encoder encoder = Base64.getEncoder();//base64编码

    ArticleProcessor(Document document,String targetUrl){
        this.document=document;
        docStr=document.toString();
        this.targetUrl=targetUrl;
        disposeStyle();
        disposeImgResource();
    }
        /**
         * 获取文章的标题
         * @return
         */
    public abstract String getTitle();

    /**
     * 处理文章样式
     */
    public abstract void disposeStyle();

    /**
     * 处理图片资源
     */
    public abstract void disposeImgResource();

    /**
     * 下载图片到nginx Web服务器
     * @param targetUrl
     * @param fileName
     */
    public void downloadImage(String targetUrl,String fileName){
        HttpUtils.download(targetUrl, GunsProperties.WEB_SERVER_PATH+"/web-imgs/"+fileName,null);
    }

    /**
     * 获取文章的浏览量
     * @return
     */
    public abstract long lookCount();


    /**
     * 获取文章的类型
     * @return
     */
    public abstract Integer type();


}

package com.stylefeng.guns.modular.system.service.processor;

import com.stylefeng.guns.config.properties.GunsProperties;
import com.stylefeng.guns.core.util.*;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Slf4j
public abstract class VideoProcessor {

    public String previews;//预览图
    protected String targetUrl;
    protected GunsProperties properties;
    final Base64.Encoder encoder = Base64.getEncoder();//base64编码

    VideoProcessor(String targetUrl){
        properties = SpringUtil.getBean(GunsProperties.class);
        this.targetUrl=targetUrl;
    }
    /**
     * 获取标题
     * @return
     */
    public abstract String getTitle();

    /**
     * 获取播放视频路径
     * @return
     */
    public abstract String getPlayVideoUrl();

    /**
     * 获取预览图路径
     * @return
     */
    public abstract String getPreviews();

    /**
     * 获取喜欢量
     * @return
     */
    public abstract long getLikeCount();

    /**
     * 获取评论量
     * @return
     */
    public abstract long getCommentCount();


    /**
     * 下载图片到nginx Web服务器
     * @param targetUrl
     * @param fileName
     */
    public void downloadImage(String targetUrl,String fileName){
        HttpUtils.download(targetUrl, properties.getWebServerPath()+"/web-imgs/"+fileName,null);
    }

    /**
     * 下载视频mp4
     */
    public void downloadVideo(){
        String videoUrl=getVideoUrl(targetUrl);
        videoUrl=properties.getWebServerPath()+"/videos/"+videoUrl;
        HttpUtils.download(getPlayVideoUrl(),videoUrl,null);
        createTS(videoUrl);
    }


    /**
     * 通过mp4文件生成 .ts 流文件
     * @param mp4Path
     */
    public void createTS(String mp4Path){
        //获取执行命令，音量和比特率可以不传
        List<String> ffmpegCommand = CommandUtils.getFfmpegCommand(mp4Path, mp4Path.replace(".mp4",".ts"));
        //执行转换
        try {
            CommandUtils.process(ffmpegCommand);
        } catch (Exception e) {
           log.error("生成 .ts 流文件失败，mp4文件名：{},exception:{}",mp4Path,e);
        }
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


    /**
     * 通过源视频分享链接构造视频存储路径
     * @param targetUrl
     * @return
     */
    public static String getVideoUrl(String targetUrl){
       return DateUtil.getDays()+"/"+ MD5Util.encrypt(targetUrl)+".mp4";
    }

    public static String getSimpleVideoUrl(String targetUrl){
        return MD5Util.encrypt(targetUrl)+".mp4";
    }


}

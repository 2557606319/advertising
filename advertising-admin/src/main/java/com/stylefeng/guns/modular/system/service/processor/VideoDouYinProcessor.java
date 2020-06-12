package com.stylefeng.guns.modular.system.service.processor;

import com.alibaba.fastjson.JSONObject;
import com.stylefeng.guns.core.util.DateUtil;
import com.stylefeng.guns.core.util.HttpUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;


/**
 * 抖音视频素材处理器
 */
public class VideoDouYinProcessor extends VideoProcessor{

    private String h5PageBody;
    //抖音视频itemId;
    private String itemId;
    //获取抖音视频信息接口
    private final String itemInfoUrl="https://www.iesdouyin.com/web/api/v2/aweme/iteminfo/?item_ids=";

    private JSONObject videoItemInfo;

    VideoDouYinProcessor(Document document, String targetUrl){
        super(document,targetUrl);
        initH5PageBody();
    }

    @Override
    public String getTitle() {
        return videoItemInfo.getJSONArray("item_list").getJSONObject(0).getString("desc");
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


    /**
     * 获取下载视频URL
     */
    @Override
    public String getPlayVideoUrl(){
        int sidx=super.docStr.indexOf("playAddr:");
        int sidx1 = super.docStr.indexOf("\"",sidx);
        int eidx = super.docStr.indexOf("\"",sidx1+1);
        String targetVideoUrl = super.docStr.substring(sidx1+1,eidx);
        targetVideoUrl=targetVideoUrl.replace("playwm","play");
        return targetVideoUrl;
    }



    /**
     * 解析预览图
     */
    public String getPreviews(){
        int sidx=super.docStr.indexOf("cover:");
        int sidx1 = super.docStr.indexOf("\"",sidx);
        int eidx = super.docStr.indexOf("\"",sidx1+1);
        return super.docStr.substring(sidx1+1,eidx);
    }

    @Override
    public long getLikeCount() {
        return videoItemInfo.getJSONArray("item_list").getJSONObject(0).getJSONObject("statistics").getLong("digg_count");
    }

    @Override
    public long getCommentCount() {
        return videoItemInfo.getJSONArray("item_list").getJSONObject(0).getJSONObject("statistics").getLong("comment_count");
    }

    /**
     * 获取抖音分享链接的h5页面html代码
     */
    private void initH5PageBody(){
        targetUrl=targetUrl.trim();
        if(targetUrl.indexOf("http")==-1){
            targetUrl="http://"+targetUrl;
        }
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            //创建Http Post请求，获取html页面
            HttpGet httpGet = new HttpGet(targetUrl);
            //模拟H5手机端请求页面
            httpGet.setHeader("User-Agent","Mozilla/5.0 (iPhone; CPU iPhone OS 11_0 like Mac OS X) AppleWebKit/604.1.38 (KHTML, like Gecko) Version/11.0 Mobile/15A372 Safari/604.1");
            // 执行http请求
            response = httpClient.execute(httpGet);
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");

            h5PageBody=resultString;

            //获取itemId
            int sidx=h5PageBody.indexOf("itemId:");
            int sidx1 = h5PageBody.indexOf("\"",sidx);
            int eidx = h5PageBody.indexOf("\"",sidx1+1);
            String itemId=h5PageBody.substring(sidx1+1,eidx);

            //请求iteminfo
            httpGet = new HttpGet(itemInfoUrl+itemId);
            response = httpClient.execute(httpGet);
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
            videoItemInfo=JSONObject.parseObject(resultString);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }



}

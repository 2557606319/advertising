package com.stylefeng.guns.modular.system.service.processor;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.stylefeng.guns.core.util.HttpUtils.USER_AGENT_IPHONE;

/**
 * 抖音视频素材处理器
 */
@Slf4j
public class VideoDouYinProcessor extends VideoProcessor {

    private String h5PageBody;
    //获取抖音视频信息接口
    private final String itemInfoUrl = "https://www.iesdouyin.com/web/api/v2/aweme/iteminfo/?item_ids=";

    private JSONObject videoInfo;

    VideoDouYinProcessor(String targetUrl) {
        super(targetUrl);
        initVideoInfos();
    }

    @Override
    public String getTitle() {
        return videoInfo.getString("desc");
    }

    @Override
    public long lookCount() {
        String title = getTitle();
        return title.length() * 100 + title.length();
    }

    @Override
    public Integer type() {
        return null;
    }

    /**
     * 获取下载视频URL
     */
    @Override
    public String getPlayVideoUrl() {
        String playUrl = videoInfo.getJSONObject("video").getJSONObject("play_addr").getJSONArray("url_list").getString(0);
        return playUrl.replace("playwm", "play");//替换为无水印视频url
    }

    /**
     * 解析预览图
     */
    public String getPreviews() {
        return videoInfo.getJSONObject("video").getJSONObject("cover").getJSONArray("url_list").getString(0);
    }

    @Override
    public long getLikeCount() {
        return videoInfo.getJSONObject("statistics").getLong("digg_count");
    }

    @Override
    public long getCommentCount() {
        return videoInfo.getJSONObject("statistics").getLong("comment_count");
    }

    /**
     * 获取抖音分享链接的h5页面html代码
     */
    private void initVideoInfos() {
        targetUrl = targetUrl.trim();
        if (targetUrl.indexOf("http") == -1) {
            targetUrl = "http://" + targetUrl;
        }
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        try {
            //获取抖音视频itemId
            Map<String, String> header = new HashMap<>();
            header.put("User-Agent", USER_AGENT_IPHONE);
            Document document = Jsoup.connect(targetUrl).headers(header)
                    .timeout(100000)
                    .ignoreContentType(true)
                    .get();
            String location = document.location();
            int sIdx = location.indexOf("video/");
            int eIdx = location.indexOf("/?");
            String itemId = location.substring(sIdx + 6, eIdx);
            String resultString = "";
            //请求iteminfo
            HttpGet httpGet = new HttpGet(itemInfoUrl + itemId);
            response = httpClient.execute(httpGet);
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
            videoInfo = JSONObject.parseObject(resultString).getJSONArray("item_list").getJSONObject(0);
        } catch (Exception e) {
            log.error("init video info error ", e);
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

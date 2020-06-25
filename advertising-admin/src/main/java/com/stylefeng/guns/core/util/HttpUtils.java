package com.stylefeng.guns.core.util;

import com.stylefeng.guns.modular.system.service.processor.VideoProcessor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import java.io.*;
import java.util.HashMap;
import java.util.Map;


@Slf4j
public class HttpUtils {

    public static boolean download(String targetUrl, String saveFileUrl, Map<String,String> headers){
        try{
            if(headers==null)
                headers=new HashMap<>();
            headers.put("User-Agent","Mozilla/5.0 (iPhone; CPU iPhone OS 11_0 like Mac OS X) AppleWebKit/604.1.38 (KHTML, like Gecko) Version/11.0 Mobile/15A372 Safari/604.1");
            BufferedInputStream in = Jsoup.connect(targetUrl).headers(headers).timeout(100000).ignoreContentType(true).execute().bodyStream();
            File saveFIle=new File(saveFileUrl);
            File fileParent = saveFIle.getParentFile();
            if (!fileParent.exists()) { fileParent.mkdirs(); }
            FileOutputStream fos=new FileOutputStream(saveFIle);
            OutputStream out = new BufferedOutputStream(fos);
            int b;
            while ((b = in.read()) != -1){
                out.write(b);
            }
            out.close();
            return true;
        }catch (Exception e){
           log.error("download error {},url:{}",e);
        }
        return false;
    }


    /**
     * 下载html代码
     * @param url
     * @return
     */
    public static String getHtml(String url) {
        url=url.trim();
        if(url.indexOf("http")==-1){
            url="http://"+url;
        }
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            // 创建Http Post请求
            HttpGet httpGet = new HttpGet(url);
            httpGet.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64; rv:46.0) Gecko/20100101 Firefox/46.0");
            // 执行http请求
            response = httpClient.execute(httpGet);
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
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

        return resultString;
    }

}

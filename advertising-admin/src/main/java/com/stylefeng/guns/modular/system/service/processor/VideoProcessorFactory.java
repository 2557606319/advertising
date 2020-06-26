package com.stylefeng.guns.modular.system.service.processor;

import com.stylefeng.guns.core.util.VideoMaterialEnum;
import org.jsoup.nodes.Document;

public class VideoProcessorFactory {
      public static VideoProcessor newInstance(String targetUrl){
          if(targetUrl.indexOf(VideoMaterialEnum.DOUYIN.getMessage())!=-1)
              return new VideoDouYinProcessor(targetUrl);

          return null;
      }
}

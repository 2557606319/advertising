package com.stylefeng.guns.modular.api;

import com.stylefeng.guns.modular.api.dto.ArticleDto;
import com.stylefeng.guns.modular.api.dto.VideoDto;
import com.stylefeng.guns.modular.api.vo.ResultBody;
import com.stylefeng.guns.modular.system.dao.ArticleTaskMapper;
import com.stylefeng.guns.modular.system.dao.VideoTaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/restApi")
public class RestTaskController {

    @Autowired
    private VideoTaskMapper videoTaskMapper;

    @Autowired
    private ArticleTaskMapper articleTaskMapper;

    @GetMapping("/task/videos")
    public ResultBody videoTaskList(VideoDto videoDto){
       List<VideoDto> videoDtos = videoTaskMapper.taskVideoList(videoDto);
       return new ResultBody(videoDtos);
    }

    @GetMapping("/task/articles")
    public ResultBody articleTaskList(ArticleDto articleDto){
        List<ArticleDto> articleDtos = articleTaskMapper.taskArticleList(articleDto);
        return new ResultBody(articleDtos);
    }

}

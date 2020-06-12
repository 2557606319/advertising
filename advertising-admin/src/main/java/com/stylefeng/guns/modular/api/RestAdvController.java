package com.stylefeng.guns.modular.api;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.exception.GunsException;
import com.stylefeng.guns.core.exception.GunsRestExceptionEnum;
import com.stylefeng.guns.core.util.AssertUtils;
import com.stylefeng.guns.core.util.HttpUtils;
import com.stylefeng.guns.core.util.JwtTokenUtil;
import com.stylefeng.guns.core.util.VideoMaterialEnum;
import com.stylefeng.guns.modular.api.dto.VideoDto;
import com.stylefeng.guns.modular.api.vo.ResultBody;
import com.stylefeng.guns.modular.system.dao.*;
import com.stylefeng.guns.modular.system.model.*;
import com.stylefeng.guns.modular.system.service.IAdvertisingService;
import com.stylefeng.guns.modular.system.service.processor.VideoProcessor;
import com.stylefeng.guns.modular.system.service.processor.VideoProcessorFactory;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;

/**
 * 广告模块controller
 * @author joey
 */
@Slf4j
@RestController
@RequestMapping("/restApi")
public class RestAdvController extends BaseController {
    @Autowired
    IAdvertisingService advertisingService;

    @Autowired
    AdvertisingMapper advertisingMapper;

    @Autowired
    ArticleIssueMapper articleIssueMapper;

    @Autowired
    VideoIssueMapper videoIssueMapper;

    @Autowired
    ArticleTaskMapper articleTaskMapper;
    /**
     * 查询用户的广告列表
     * @return
     */
    @GetMapping("/auth/adv/advList")
    public ResultBody advList(){
        long uid=JwtTokenUtil.getClientUserIdFromToken();
        HashMap<String,Object> where=new HashMap<>(2);
        where.put("user_id",uid);
        List<Advertising> advertisings = advertisingService.selectByMap(where);
        for (Advertising advertising:advertisings) {
            where.put("advertising_id",advertising.getId());
            List<ArticleTask> articleTasks = articleTaskMapper.selectByMap(where);
            if(articleTasks.size()>0)advertising.setTaskStatus(1);
        }
        return new ResultBody(advertisings);
    }

    @PostMapping("/auth/adv/setDefault/{id}")
    @Transactional
    public ResultBody setDefault(@PathVariable Long id){
        if(id!=null){
            advertisingMapper.noDefault(JwtTokenUtil.getClientUserIdFromToken());
            Advertising advertising=new Advertising();
            advertising.setId(id);
            advertising.setIsDefault(1);
            advertisingService.updateById(advertising);
        }
        return new ResultBody(true);
    }

    @PostMapping("/auth/adv/addOrUpd")
    public ResultBody addAdv(Advertising advertising){
        advertising.setUserId(JwtTokenUtil.getClientUserIdFromToken());
        advertisingService.insertOrUpdate(advertising);
        return new ResultBody(true);
    }

    @PostMapping("/auth/adv/del/{id}")
    public ResultBody delAdvById(@PathVariable Long id){
        advertisingService.deleteById(id);
        return new ResultBody(true);
    }

    @PostMapping("/auth/adv/upd")
    public ResultBody upd(Advertising advertising){
        advertisingService.updateById(advertising);
        return new ResultBody(true);
    }

    @GetMapping("/adv/select/{id}")
    public ResultBody selectById(@PathVariable Long id){
       return new ResultBody(advertisingService.selectById(id));
    }

    @PostMapping("/auth/adv/addTask")
    @Transactional
    public ResultBody addTask(Advertising advertising){

        AssertUtils.isLtOne(advertising.getAward(),GunsRestExceptionEnum.ARGS_ERR.getMessage());
        AssertUtils.isLtOne(advertising.getSumAward(),GunsRestExceptionEnum.ARGS_ERR.getMessage());

        boolean update = advertisingService.updateById(advertising);
        if(!update)
            throw new GunsException(GunsRestExceptionEnum.UPD_TASK_ERR);

        long uid = JwtTokenUtil.getClientUserIdFromToken();
        //查询所有装载该广告的素材(图片、视频)
        Map<String,Object> where = new HashMap<>(2);
        where.put("user_id",uid);
        where.put("advertising_id",advertising.getId());

        List<ArticleTask> articleTasks = articleTaskMapper.selectByMap(where);

        //第一次发布任务
        if(articleTasks.size()==0){
            ArticleTask articleTask= new ArticleTask();
            articleTask.setAdvertisingId(advertising.getId());
            articleTask.setExpire(advertising.getExpire());
            articleTask.setUserId(uid);
            articleTask.setSumAward(advertising.getSumAward());
            articleTask.setAward(advertising.getAward());
            articleTaskMapper.insert(articleTask);

            VideoTask videoTask= new VideoTask();
            videoTask.setAdvertisingId(advertising.getId());
            videoTask.setExpire(advertising.getExpire());
            videoTask.setUserId(uid);
            videoTask.setSumAward(advertising.getSumAward());
            videoTask.setAward(advertising.getAward());
            articleTaskMapper.insert(articleTask);

            //绑定任务
            videoIssueMapper.settingTask(uid,advertising.getId(),articleTask.getId());
            articleIssueMapper.settingTask(uid,advertising.getId(),articleTask.getId());
        }else{
            ArticleTask articleTask= articleTasks.get(0);
            articleTask.setExpire(advertising.getExpire());
            articleTask.setSumAward(advertising.getSumAward());
            articleTask.setAward(advertising.getAward());
            articleTaskMapper.updateById(articleTask);
        }
        return new ResultBody(true);
    }

    /**
     * 查询当前用户的默认设置广告
     * @return
     */
    @GetMapping("/auth/adv/defaultAdvertising")
    public ResultBody defaultAdvertising(){
        Long uid=JwtTokenUtil.getClientUserIdFromToken();
        List<Advertising> list=advertisingMapper.defaultAdv(uid);
        if(list.size()==0){
            return new ResultBody(null);
        }
        return new ResultBody(list.get(0));
    }


}


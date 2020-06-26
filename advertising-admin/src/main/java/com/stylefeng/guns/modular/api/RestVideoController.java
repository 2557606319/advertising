package com.stylefeng.guns.modular.api;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.exception.GunsException;
import com.stylefeng.guns.core.exception.GunsRestExceptionEnum;
import com.stylefeng.guns.core.util.*;
import com.stylefeng.guns.modular.api.dto.ClientUserDto;
import com.stylefeng.guns.modular.api.dto.VideoDto;
import com.stylefeng.guns.modular.api.vo.ResultBody;
import com.stylefeng.guns.modular.system.dao.*;
import com.stylefeng.guns.modular.system.model.*;
import com.stylefeng.guns.modular.system.service.IClientUserService;
import com.stylefeng.guns.modular.system.service.processor.VideoProcessor;
import com.stylefeng.guns.modular.system.service.processor.VideoProcessorFactory;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;

/**
 * 视频模块controller
 *
 * @author joey
 */
@Slf4j
@RestController
@RequestMapping("/restApi")
public class RestVideoController extends BaseController {

    @Autowired
    private TypeMapper typeMapper;

    @Autowired
    private VideoMapper videoMapper;

    @Autowired
    private VideoCommentMapper videoCommentMapper;

    @Autowired
    private VideoLikeMapper videoLikeMapper;

    @Autowired
    private VideoIssueMapper videoIssueMapper;

    @Autowired
    private IClientUserService clientUserService;
    @Autowired
    private VideoIssueMapper issueMapper;

    @Autowired
    private VideoTaskMapper videoTaskMapper;

    final Base64.Decoder decoder = Base64.getDecoder();

    @ApiOperation("首页视频列表展示")
    @PostMapping("/video/list")
    public ResultBody list(VideoDto videoDto) {
        List<Video> videos = new ArrayList<>();
        videos = videoMapper.list(videoDto);
        return new ResultBody(videos);
    }


    /**
     * 通过第三方短视频平台的分享链接 获取视频内容
     *
     * @param targetUrl 文章素材目标url
     * @param typeId    文章素材类型
     * @throws IOException
     */
    @PostMapping("/auth/video/collect")
    @Transactional
    public ResultBody getVideoInfo(String targetUrl, Integer typeId) throws IOException {
        long uid = JwtTokenUtil.getClientUserIdFromToken();
        targetUrl = new String(decoder.decode(targetUrl), "UTF-8");//解码 base64 url
        targetUrl = targetUrl.trim();
        String suffix = targetUrl.substring(targetUrl.length() - 1);
        if (suffix.equals("/"))
            targetUrl = targetUrl.substring(0, targetUrl.length() - 1);
        //检测是否属于适配域名之内
        if (!VideoMaterialEnum.check(targetUrl))
            throw new GunsException(GunsRestExceptionEnum.NO_SUPPORT_VIDEO);
        //处理url为md5字符串作为文件名
        String url = VideoProcessor.getVideoUrl(targetUrl);
        //检测web服务目录中是否存在
        List<Video> videos = videoMapper.findVideoByUrl(VideoProcessor.getSimpleVideoUrl(targetUrl));
        VideoDto result = null;
        if (videos.size() > 0) {//素材已经存在
            result = new VideoDto();
            BeanUtils.copyProperties(videos.get(0), result);
        } else {
            //文章素材处理
            VideoProcessor processor = VideoProcessorFactory.newInstance(targetUrl);
            try {
                if (processor == null)
                    throw new GunsException(GunsRestExceptionEnum.NO_SUPPORT_VIDEO);
                processor.downloadVideo();
                Video video = new Video();
                video.setTitle(processor.getTitle());
                video.setTypeId(typeId);
                video.setUrl(url);
                video.setUserId(uid);
                video.setPreviews(processor.getPreviews());
                video.setLookCount(processor.lookCount());
                video.setTypeId(typeId);
                video.setLikeCount(processor.getLikeCount());
                video.setCommentCount(processor.getCommentCount());

                videoMapper.insert(video);

                result = new VideoDto();
                BeanUtils.copyProperties(video, result);
            } catch (Exception e) {
                log.error("video采集失败，分享url地址为：{},异常信息：{}", targetUrl, e);
            }
        }

        Map<String, Object> where = new HashMap<>(1);
        where.put("video_id", result.getId());
        where.put("user_id", uid);
        List<VideoIssue> videoIssues = videoIssueMapper.selectByMap(where);
        if (videoIssues.size() == 0) {
            //关联为用户的发布
            VideoIssue videoIssue = new VideoIssue();
            videoIssue.setUserId(JwtTokenUtil.getClientUserIdFromToken());
            videoIssue.setVideoId(result.getId());
            videoIssueMapper.insert(videoIssue);
        } else {
            result.setAdvId(videoIssues.get(0).getAdvertisingId());
        }
        return new ResultBody(result);
    }

    /**
     * 根据id查询video信息
     *
     * @param id
     * @return
     */
    @GetMapping("/video/info/{id}")
    public ResultBody getExistInfo(@PathVariable Long id) {
        Video video = videoMapper.selectById(id);
        VideoDto result = new VideoDto();
        BeanUtils.copyProperties(video, result);
        Map<String, Object> where = new HashMap<>(1);
        where.put("video_id", id);
        try {
            where.put("user_id", JwtTokenUtil.getClientUserIdFromToken());
        } catch (Exception e) {
            return new ResultBody(result);
        }
        List<VideoIssue> videoIssues = videoIssueMapper.selectByMap(where);
        if (videoIssues.size() > 0)
            result.setAdvId(videoIssues.get(0).getAdvertisingId());

        return new ResultBody(result);
    }


    /**
     * 获取评论列表
     *
     * @param id
     * @return
     */
    @GetMapping("/video/comments/{id}")
    public ResultBody commentListById(@PathVariable Long id) {
        HashMap<String, Object> where = new HashMap<>();
        where.put("relation_id", id);
        List<VideoComment> comments = videoCommentMapper.selectByMap(where);
        return new ResultBody(comments);
    }

    /**
     * 收藏与取消搜藏
     *
     * @param id
     * @return
     */
    @GetMapping("/auth/video/like/{id}")
    public ResultBody likeVideoById(@PathVariable Long id) {
        long uid = JwtTokenUtil.getClientUserIdFromToken();
        HashMap<String, Object> where = new HashMap<>();
        where.put("relation_id", id);
        where.put("uid", uid);

        List<VideoLike> likes = videoLikeMapper.selectByMap(where);
        if (likes.size() > 0) {
            videoLikeMapper.deleteById(likes.get(0).getId());
        } else {
            VideoLike videoLike = new VideoLike();
            videoLike.setRelationId(id);
            videoLike.setUid(uid);
            videoLikeMapper.insert(videoLike);
        }
        return new ResultBody(true);
    }


    /**
     * 是否收藏
     *
     * @param id
     * @return
     */
    @GetMapping("/auth/video/isLike/{id}")
    public ResultBody isLike(@PathVariable Long id) {
        long uid = JwtTokenUtil.getClientUserIdFromToken();
        HashMap<String, Object> where = new HashMap<>();
        where.put("relation_id", id);
        where.put("uid", uid);
        List<VideoLike> likes = videoLikeMapper.selectByMap(where);
        if (likes.size() > 0)
            return new ResultBody(true);

        return new ResultBody(false);
    }


    /**
     * 根据issue id查询视频素材
     *
     * @param id
     * @return
     */
    @GetMapping("/video/issue/info/{id}")
    public ResultBody videoInfoByIssueId(@PathVariable Long id) {
        VideoIssue videoIssue = videoIssueMapper.selectById(id);
        Video video = videoMapper.selectById(videoIssue.getVideoId());
        VideoDto result = new VideoDto();
        result.setAdvId(videoIssue.getAdvertisingId());
        BeanUtils.copyProperties(video, result);
        return new ResultBody(result);
    }

    /**
     * 查看发布人id
     *
     * @param id
     * @return
     */
    @GetMapping("/video/issue/userInfo/{id}")
    public ResultBody issueUserInfo(@PathVariable Long id) {
        VideoIssue videoIssue = videoIssueMapper.selectById(id);
        long userId = videoIssue.getUserId();
        ClientUser clientUser = clientUserService.selectById(userId);
        ClientUserDto clientUserDto = new ClientUserDto();
        BeanUtils.copyProperties(clientUser, clientUserDto);
        return new ResultBody(clientUserDto);
    }

    /**
     * 发布分享视频
     *
     * @param vid   视频id
     * @param advId 广告id
     * @param type  分享类型
     * @return
     */
    @PostMapping("/auth/video/issue")
    @Transactional
    public ResultBody issueVideo(Long vid, Long advId, Integer type) {
        settingVideoAdvId(vid, advId);
        //微信分享三方接口处理

        return new ResultBody(true);
    }

    /**
     * 设置视频发布的广告id
     *
     * @param vid
     * @param advId
     * @return
     */
    @PostMapping("/auth/video/setIssueAdvId")
    public ResultBody settingVideoAdvId(Long vid, Long advId) {
        Video video = videoMapper.selectById(vid);
        AssertUtils.notNull(video, GunsRestExceptionEnum.VIDEO_NO_EXIST.getMessage());
        long uid = JwtTokenUtil.getClientUserIdFromToken();
        Map<String, Object> where = new HashMap<>(2);
        where.put("video_id", vid);
        where.put("user_id", uid);
        List<VideoIssue> videoIssue = videoIssueMapper.selectByMap(where);
        VideoIssue issue = null;
        if (videoIssue.size() == 0) {
            issue = new VideoIssue();
            issue.setVideoId(vid);
            issue.setUserId(uid);
            issue.setAdvertisingId(advId);
            where.clear();
            where.put("advertising_id", advId);
            List<VideoTask> tasks = videoTaskMapper.selectByMap(where);
            if (tasks.size() > 0) issue.setTaskId(tasks.get(0).getId());
            videoIssueMapper.insert(issue);
        } else {
            issue = videoIssue.get(0);
            issue.setAdvertisingId(advId);
            videoIssueMapper.updateById(issue);
        }
        return new ResultBody(issue);
    }

    @GetMapping("/auth/video/issueList")
    public ResultBody issueList() {
        long uid = JwtTokenUtil.getClientUserIdFromToken();
        HashMap<String, Object> where = new HashMap<>(1);
        where.put("user_id", uid);
        List<VideoIssue> videoIssues = issueMapper.selectByMap(where);
        List<Long> ids = new ArrayList<>();
        videoIssues.forEach((issue) -> {
            ids.add(issue.getVideoId());
        });
        return new ResultBody(videoMapper.selectBatchIds(ids));
    }


}


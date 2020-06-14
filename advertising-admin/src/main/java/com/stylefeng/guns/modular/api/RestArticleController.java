package com.stylefeng.guns.modular.api;

import com.stylefeng.guns.config.properties.GunsProperties;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.exception.GunsException;
import com.stylefeng.guns.core.exception.GunsRestExceptionEnum;
import com.stylefeng.guns.core.util.*;
import com.stylefeng.guns.modular.api.dto.ArticleDto;
import com.stylefeng.guns.modular.api.dto.ClientUserDto;
import com.stylefeng.guns.modular.api.dto.VideoDto;
import com.stylefeng.guns.modular.api.vo.ResultBody;
import com.stylefeng.guns.modular.system.dao.*;
import com.stylefeng.guns.modular.system.model.*;
import com.stylefeng.guns.modular.system.service.IArticleService;
import com.stylefeng.guns.modular.system.service.IArticleTaskService;
import com.stylefeng.guns.modular.system.service.IClientUserService;
import com.stylefeng.guns.modular.system.service.IUserService;
import com.stylefeng.guns.modular.system.service.processor.ArticleProcessor;
import com.stylefeng.guns.modular.system.service.processor.ArticleProcessorFactory;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * 文章模块controller
 *
 * @author joey
 */
@RestController
@RequestMapping("/restApi")
@Slf4j
public class RestArticleController extends BaseController {

    final Base64.Decoder decoder = Base64.getDecoder();


    @Autowired
    private TypeMapper typeMapper;

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private IArticleService articleService;

    @Autowired
    private ArticleIssueMapper articleIssueMapper;

    @Autowired
    private ArticleCommentMapper articleCommentMapper;

    @Autowired
    private AdvertisingMapper advertisingMapper;

    @Autowired
    private ArticleIssueMapper issueMapper;

    @Autowired
    private IClientUserService clientUserService;

    @Autowired
    private IArticleTaskService articleTaskService;

    @ApiOperation("首页素材列表展示")
    @PostMapping("/article/list")
    public ResultBody list(ArticleDto articleDto) {
        List<Article> articles = new ArrayList<>();
        articles = articleMapper.list(articleDto);
        return new ResultBody(articles);
    }


    /**
     * 通过第三方文章链接 获取文章静态html
     *
     * @param targetUrl 文章素材目标url
     * @param typeId    文章素材类型
     * @throws IOException
     */
    @PostMapping("/auth/article/collect")
    public ResultBody WStoHtml(String targetUrl, Integer typeId) throws IOException {
        targetUrl = new String(decoder.decode(targetUrl), "UTF-8");//解码 base64 url

        //url做长度处理，一般&后面的追加参数都不重要  可以截取掉
        if (targetUrl.indexOf("&") != -1)
            targetUrl = targetUrl.substring(0, targetUrl.indexOf("&"));

        //检测是否属于适配域名之内
        if (!ArticleMaterialEnum.check(targetUrl))
            throw new GunsException(GunsRestExceptionEnum.NO_SUPPORT_ARTICLE);

        //处理url为md5字符串作为文件名
        String url = MD5Util.encrypt(targetUrl) + ".html";
        //检测web服务目录中是否存在
        Article article = articleMapper.articleNumByUrl(url);
        ArticleDto result = new ArticleDto();
        if (article != null) {//文章素材已经存在
            result.setId(article.getId());
            result.setUrl(url);
            result.setLookCount(article.getLookCount());
            result.setLooking(article.getLooking());
            return new ResultBody(result);

        }

        String body = HttpUtils.getHtml(targetUrl);//body为获取的html代码
        Document doc = Jsoup.parse(body);

        //文章素材处理
        ArticleProcessor processor = ArticleProcessorFactory.newInstance(targetUrl, doc);

        FileWriter writer;
        try {
            File file = new File(GunsProperties.WEB_SERVER_PATH + "/article/" + url);
            writer = new FileWriter(file);
            writer.write(doc.toString());
            writer.flush();
            writer.close();

            if (processor == null)
                throw new GunsException(GunsRestExceptionEnum.NO_SUPPORT_ARTICLE);

            if (processor.getTitle() == null || processor.getTitle().equals(""))
                throw new GunsException(GunsRestExceptionEnum.ARTICLE_NO_EXIST);

            article = new Article();
            article.setTitle(processor.getTitle());
            article.setTypeId(typeId);
            article.setUrl(url);
            article.setUserId(JwtTokenUtil.getClientUserIdFromToken());
            article.setPreviews(processor.getFirstThreeImgUrl());
            article.setLookCount(processor.lookCount());
            article.setLooking((long) (article.getTitle().length() * 2));//在看 数据为假的
            article.setTypeId(typeId);
            articleService.insert(article);

            result.setUrl(url);
            result.setId(article.getId());
            result.setLookCount(article.getLookCount());
            result.setLooking(article.getLooking());

            return new ResultBody(result);


        } catch (IOException e) {
            log.error("html输出至web服务目录错误，文件名为：{},异常信息：{}", url, e.getMessage());
        }
        return new ResultBody(null);
    }

    /**
     * 根据id查询文章素材
     *
     * @param id
     * @return
     */
    @GetMapping("/article/info/{id}")
    public ResultBody getExistInfo(@PathVariable Long id) {
        Article article = articleService.selectById(id);
        ArticleDto result = new ArticleDto();
        BeanUtils.copyProperties(article, result);
        Map<String, Object> where = new HashMap<>(1);
        where.put("article_id", id);
        try {
            where.put("user_id", JwtTokenUtil.getClientUserIdFromToken());
        } catch (Exception e) {
        }
        List<ArticleIssue> issues = issueMapper.selectByMap(where);
        if (issues.size() > 0)
            result.setAdvId(issues.get(0).getAdvertisingId());
        return new ResultBody(result);
    }

    /**
     * 根据issue id查询文章素材
     * @param id
     * @return
     */
    @GetMapping("/article/issue/info/{id}")
    public ResultBody articleInfoByIssueId(@PathVariable Long id) {
        ArticleIssue articleIssue = articleIssueMapper.selectById(id);
        Article article = articleService.selectById(articleIssue.getArticleId());
        ArticleDto result = new ArticleDto();
        result.setAdvId(articleIssue.getAdvertisingId());
        BeanUtils.copyProperties(article, result);
        return new ResultBody(result);
    }

    /**
     * 查看发布人id
     * @param id
     * @return
     */
    @GetMapping("/article/issue/userInfo/{id}")
    public ResultBody issueUserInfo(@PathVariable Long id) {
        ArticleIssue articleIssue = articleIssueMapper.selectById(id);
        long userId = articleIssue.getUserId();
        ClientUser clientUser=clientUserService.selectById(userId);
        ClientUserDto clientUserDto=new ClientUserDto();
        BeanUtils.copyProperties(clientUser,clientUserDto);
        return new ResultBody(clientUserDto);
    }

    @GetMapping("/article/comments/{id}")
    public ResultBody commentListById(@PathVariable Long id) {
        HashMap<String, Object> where = new HashMap<>();
        where.put("relation_id", id);
        List<ArticleComment> comments = articleCommentMapper.selectByMap(where);
        return new ResultBody(comments);
    }


    @GetMapping("/auth/article/issueList")
    public ResultBody issueList() {
        long uid = JwtTokenUtil.getClientUserIdFromToken();
        HashMap<String, Object> where = new HashMap<>(1);
        where.put("user_id", uid);
        List<ArticleIssue> issues = issueMapper.selectByMap(where);
        List<Long> ids = new ArrayList<>();
        issues.forEach((issue) -> {
            ids.add(issue.getArticleId());
        });
        if (ids.size() == 0)
            return new ResultBody(null);
        return new ResultBody(articleMapper.selectBatchIds(ids));
    }

    @PostMapping("/auth/article/issueComment")
    public ResultBody issueComment(ArticleComment comment) {
        long uid = JwtTokenUtil.getClientUserIdFromToken();
        ClientUser user = clientUserService.selectById(uid);
        comment.setAvatar(user.getAvatar());
        comment.setNick(user.getNick());
        comment.setUid(uid);
        articleCommentMapper.insert(comment);
        return new ResultBody(true);
    }

    /**
     * 发布分享视频
     * @param articleId 视频id
     * @param advId 广告id
     * @param type 分享类型
     * @return
     */
    @PostMapping("/auth/article/issue")
    @Transactional
    public ResultBody issueVideo(Long articleId,Long advId,Integer type){
        settingArticleAdvId(articleId,advId);
        //微信分享三方接口处理

        return new ResultBody(true);
    }

    /**
     * 设置文章发布的广告id
     * @param articleId
     * @param advId
     * @return
     */
    @PostMapping("/auth/article/setIssueAdvId")
    public ResultBody settingArticleAdvId(Long articleId,Long advId){
        Article article = articleService.selectById(articleId);
        AssertUtils.notNull(article,GunsRestExceptionEnum.VIDEO_NO_EXIST.getMessage());
        long uid = JwtTokenUtil.getClientUserIdFromToken();
        Map<String,Object> where = new HashMap<>(2);
        where.put("article_id",articleId);
        where.put("user_id",uid);
        List<ArticleIssue> articleIssues = articleIssueMapper.selectByMap(where);
        ArticleIssue issue = null;
        if (articleIssues.size() == 0) {
            issue = new ArticleIssue();
            issue.setArticleId(articleId);
            issue.setUserId(uid);
            issue.setAdvertisingId(advId);
            articleIssueMapper.insert(issue);
        } else {
            issue = articleIssues.get(0);
            issue.setAdvertisingId(advId);
            articleIssueMapper.updateById(issue);
        }
        return new ResultBody(issue);
    }

//    public ResultBody issueTaskList(){
//        List<ArticleTask> articleTasks = articleTaskService.selectList(null);
//
//
//    }
}


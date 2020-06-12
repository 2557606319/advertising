package com.stylefeng.guns.modular.system.dao;

import com.stylefeng.guns.modular.system.model.ArticleIssue;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 * 文章发布表 Mapper 接口
 * </p>
 *
 * @author joey
 * @since 2020-03-25
 */
public interface ArticleIssueMapper extends BaseMapper<ArticleIssue> {


    /**
     * 设置用户发布关联了对应广告文章素材的金币任务
     * @param uid
     * @param advId
     * @param taskId
     */
    @Update("update tb_article_issue set task_id = #{taskId} where user_id = #{uid} and advertising_id = #{advId}")
    void settingTask(@Param("uid")Long uid,@Param("advId")Long advId,@Param("taskId")Long taskId);

}

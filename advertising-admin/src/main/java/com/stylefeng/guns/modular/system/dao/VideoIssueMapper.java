package com.stylefeng.guns.modular.system.dao;

import com.stylefeng.guns.modular.system.model.VideoIssue;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

public interface VideoIssueMapper extends BaseMapper<VideoIssue> {

    /**
     * 设置用户发布关联了对应广告视频素材的金币任务
     * @param uid
     * @param advId
     * @param taskId
     */
    @Update("update tb_video_issue set task_id = #{taskId} where user_id = #{uid} and advertising_id = #{advId}")
    void settingTask(@Param("uid")Long uid, @Param("advId")Long advId, @Param("taskId")Long taskId);


}

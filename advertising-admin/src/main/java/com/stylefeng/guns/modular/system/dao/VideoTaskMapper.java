package com.stylefeng.guns.modular.system.dao;

import com.stylefeng.guns.modular.api.dto.VideoDto;
import com.stylefeng.guns.modular.system.model.VideoTask;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 视频金币任务表  Mapper 接口
 * </p>
 *
 * @author joey
 * @since 2020-03-25
 */
public interface VideoTaskMapper extends BaseMapper<VideoTask> {

    List<VideoDto> taskVideoList(VideoDto videoDto);
}

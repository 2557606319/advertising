package com.stylefeng.guns.modular.system.service.impl;

import com.stylefeng.guns.modular.system.model.Video;
import com.stylefeng.guns.modular.system.dao.VideoMapper;
import com.stylefeng.guns.modular.system.service.IVideoService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 视频素材表 服务实现类
 * </p>
 *
 * @author joey
 * @since 2020-03-25
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements IVideoService {

}

package com.lx.eduService.service;

import com.lx.eduService.entity.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-11-12
 */
public interface EduVideoService extends IService<EduVideo> {

    void removeByCourseId(String id);
}

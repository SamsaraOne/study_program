package com.lx.eduService.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lx.eduService.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-11-07
 */
public interface EduTeacherService extends IService<EduTeacher> {

    Map<String, Object> getListPageTeacher(Page<EduTeacher> page);
}

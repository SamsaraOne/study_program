package com.lx.eduService.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lx.eduService.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lx.eduService.entity.frontvo.BaseCourseInfo;
import com.lx.eduService.entity.frontvo.CourseFrontVo;
import com.lx.eduService.entity.vo.CourseInfoVo;
import com.lx.eduService.entity.vo.CoursePublishVo;

import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-11-12
 */
public interface EduCourseService extends IService<EduCourse> {

    CourseInfoVo getCourseInfo(String courseId);

    String addCourseInfo(CourseInfoVo courseInfo);

    void updateCourseInfo(CourseInfoVo courseInfoVo);

    CoursePublishVo getPublishCourseInfo(String id);

    void removeCourse(String id);

    Map<String, Object> selectFrontCourse(Page<EduCourse> page, CourseFrontVo condition);


    BaseCourseInfo getBaseCourseInfo(String courseId);
}

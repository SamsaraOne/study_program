package com.lx.eduService.mapper;

import com.lx.eduService.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lx.eduService.entity.frontvo.BaseCourseInfo;
import com.lx.eduService.entity.vo.CourseInfoVo;
import com.lx.eduService.entity.vo.CoursePublishVo;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2020-11-12
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {

    public CoursePublishVo getCoursepublishInfo(String courseId);

    public BaseCourseInfo getBaseCourseInfo(String courseId);

}

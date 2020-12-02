package com.lx.eduService.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lx.eduService.client.VodClient;
import com.lx.eduService.entity.EduCourse;
import com.lx.eduService.entity.EduCourseDescription;
import com.lx.eduService.entity.frontvo.BaseCourseInfo;
import com.lx.eduService.entity.frontvo.CourseFrontVo;
import com.lx.eduService.entity.vo.CourseInfoVo;
import com.lx.eduService.entity.vo.CoursePublishVo;
import com.lx.eduService.mapper.EduCourseMapper;
import com.lx.eduService.service.EduChapterService;
import com.lx.eduService.service.EduCourseDescriptionService;
import com.lx.eduService.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lx.eduService.service.EduVideoService;
import com.lx.servicebase.exceptionhandler.TestException;
import com.mysql.cj.util.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-11-12
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    @Autowired
    private EduCourseDescriptionService eduCourseDescriptionService;
    @Autowired
    private EduChapterService chapterService;
    @Autowired
    private EduVideoService videoService;


    @Override
    public CourseInfoVo getCourseInfo(String courseId) {
        EduCourse eduCourse = baseMapper.selectById(courseId);
        EduCourseDescription description = eduCourseDescriptionService.getById(courseId);
        CourseInfoVo res = new CourseInfoVo();
        BeanUtils.copyProperties(eduCourse,res);
        BeanUtils.copyProperties(description,res);
        return res;
    }

    @Override
    public String addCourseInfo(CourseInfoVo courseInfo) {
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfo,eduCourse);
        boolean isSave = this.save(eduCourse);
        if (!isSave){
            throw new TestException(20001,"添加课程失败");
        }
        String cid = eduCourse.getId();

        EduCourseDescription description = new EduCourseDescription();
        description.setId(cid);
        description.setDescription(courseInfo.getDescription());
        eduCourseDescriptionService.save(description);


        return cid;
    }

    @Override
    public void updateCourseInfo(CourseInfoVo courseInfoVo) {
        EduCourse course = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo,course);
        int update = baseMapper.updateById(course);
        if (update==0){
            throw new TestException(20001,"修改课程信息失败");
        }

        EduCourseDescription description = new EduCourseDescription();
        description.setDescription(courseInfoVo.getDescription());
        description.setId(courseInfoVo.getId());
        boolean descrip_update = eduCourseDescriptionService.updateById(description);
        if (!descrip_update){
            throw new TestException(20001,"修改课程描述失败");
        }
    }

    @Override
    public CoursePublishVo getPublishCourseInfo(String id) {
        return baseMapper.getCoursepublishInfo(id);
    }

    @Override
    public void removeCourse(String courseId) {

        //删除小节
        videoService.removeByCourseId(courseId);
        //删除章节
        chapterService.removeByCourseId(courseId);
        //删除描述
        eduCourseDescriptionService.removeById(courseId);
        //删除课程
        boolean b = this.removeById(courseId);
        if(!b){
            throw new TestException(20001,"删除失败");
        }
    }

    @Override
    public Map<String, Object> selectFrontCourse(Page<EduCourse> page, CourseFrontVo condition) {
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        if (!StringUtils.isNullOrEmpty(condition.getSubjectParentId())){
            wrapper.eq("subject_parent_id",condition.getSubjectParentId());
        }
        if (!StringUtils.isNullOrEmpty(condition.getSubjectId())){
            wrapper.eq("subject_id",condition.getSubjectId());
        }
        if (!StringUtils.isNullOrEmpty(condition.getBuyCountSort())){
            wrapper.orderByDesc("buy_count");
        }
        if (!StringUtils.isNullOrEmpty(condition.getPriceSort())){
            wrapper.orderByDesc("price");
        }
        if (!StringUtils.isNullOrEmpty(condition.getGmtCreateSort())){
            wrapper.orderByDesc("gmt_create");
        }
        baseMapper.selectPage(page,wrapper);
        List<EduCourse> records = page.getRecords();
        long current = page.getCurrent();
        long pages = page.getPages();
        long size = page.getSize();
        long total = page.getTotal();
        boolean hasNext = page.hasNext();
        boolean hasPrevious = page.hasPrevious();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("items", records);
        map.put("current", current);
        map.put("pages", pages);
        map.put("size", size);
        map.put("total", total);
        map.put("hasNext", hasNext);
        map.put("hasPrevious", hasPrevious);

        return map;

    }

    @Override
    public BaseCourseInfo getBaseCourseInfo(String courseId) {
        return baseMapper.getBaseCourseInfo(courseId);
    }
}

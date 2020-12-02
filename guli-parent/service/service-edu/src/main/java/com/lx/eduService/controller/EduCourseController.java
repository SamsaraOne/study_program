package com.lx.eduService.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lx.commonutils.R;
import com.lx.eduService.config.Educonfig;
import com.lx.eduService.entity.EduCourse;
import com.lx.eduService.entity.vo.CourseInfoVo;
import com.lx.eduService.entity.vo.CoursePublishVo;
import com.lx.eduService.entity.vo.courseQuery;
import com.lx.eduService.service.EduCourseDescriptionService;
import com.lx.eduService.service.EduCourseService;

import com.mysql.cj.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-11-12
 */
@RestController
@RequestMapping("/eduService/educourse")
@CrossOrigin
public class EduCourseController {
    @Autowired
    private EduCourseService eduCourseService;

    @PostMapping("/addCourseInfo")
    public R addCourseInfo(@RequestBody CourseInfoVo courseInfo){

        String id = eduCourseService.addCourseInfo(courseInfo);
        return R.ok().data("courseId",id);
    }


    @GetMapping("/getCourseInfo/{courseId}")
    public R getCourseInfo(@PathVariable String courseId){
        CourseInfoVo courseInfo = eduCourseService.getCourseInfo(courseId);
        return R.ok().data("courseInfo",courseInfo);
    }
    @PostMapping("/updateCourseInfo")
    public R updateCourseInfo(@RequestBody CourseInfoVo courseInfoVo){
        eduCourseService.updateCourseInfo(courseInfoVo);
        return R.ok();
    }

    @GetMapping("/getCoursePublishInfo/{id}")
    public R getCoursePublish(@PathVariable String id){
        CoursePublishVo courseInfo = eduCourseService.getPublishCourseInfo(id);
        return R.ok().data("publish",courseInfo);
    }

    @PostMapping("/publishCourse/{id}")
    public R publishCourse(@PathVariable String id){
        EduCourse course = new EduCourse();
        course.setId(id);
        course.setStatus("Normal");
        eduCourseService.updateById(course);
        return R.ok();
    }
    //条件查询带分页
    @PostMapping("/getCourseCondition/{cur}/{mount}")
    public R getCourseCondition(@PathVariable Integer cur, @PathVariable Integer mount,
                                @RequestBody(required = false) courseQuery courseQuery){
        Page<EduCourse> page = new Page<>(cur,mount);
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        String title = courseQuery.getName();
        String status = courseQuery.getStatus();
        if(!StringUtils.isNullOrEmpty(title)){
            wrapper.like("title",title);
        }
        if(!StringUtils.isNullOrEmpty(status)){
            wrapper.eq("status",status);
        }
        eduCourseService.page(page,wrapper);
        List<EduCourse> list = page.getRecords();
        Long total = page.getTotal();
        return R.ok().data("list",list).data("total",total);
    }
    //删除课程
    @DeleteMapping("/deleteCourse/{id}")
    public R deleteCourse(@PathVariable String id){
        eduCourseService.removeCourse(id);
        return R.ok();
    }

}


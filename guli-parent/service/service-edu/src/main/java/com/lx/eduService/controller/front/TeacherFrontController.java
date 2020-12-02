package com.lx.eduService.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lx.commonutils.R;
import com.lx.eduService.config.Educonfig;
import com.lx.eduService.entity.EduCourse;
import com.lx.eduService.entity.EduTeacher;
import com.lx.eduService.service.EduCourseService;
import com.lx.eduService.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/eduService/teacherFront")
public class TeacherFrontController {

    @Autowired
    private EduTeacherService teacherService;
    @Autowired
    private EduCourseService courseService;

    @PostMapping("/pageTeacher/{cur}/{limit}")
    public R getTeacherFront(@PathVariable long cur, @PathVariable long limit){
        Page<EduTeacher> page = new Page<>(cur,limit);
        Map<String, Object> map = teacherService.getListPageTeacher(page);
        return R.ok().data(map);
    }

    @GetMapping("/getInfo/{id}")
    public R get(@PathVariable String id){
        EduTeacher teacher = teacherService.getById(id);
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        wrapper.eq("teacher_id",id);
        List<EduCourse> courses = courseService.list(wrapper);
        return R.ok().data("teacher",teacher).data("courses",courses);

    }
}

package com.lx.eduService.controller.front;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lx.commonutils.R;
import com.lx.eduService.entity.EduCourse;
import com.lx.eduService.entity.EduTeacher;
import com.lx.eduService.service.EduCourseService;
import com.lx.eduService.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/eduService/indexFront")
public class IndexFrontController {
    @Autowired
    private EduCourseService courseService;
    @Autowired
    private EduTeacherService teacherService;

    @GetMapping("/index")
    public R getIndex(){
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("view_count");
        wrapper.last("limit 8");
        List<EduCourse> course = courseService.list(wrapper);
        QueryWrapper<EduTeacher> wrapper1 = new QueryWrapper<>();
        wrapper1.orderByDesc("id");
        wrapper1.last("limit 4");
        List<EduTeacher> res = teacherService.list(wrapper1);
        return R.ok().data("course",course).data("teacher",res);

    }

}

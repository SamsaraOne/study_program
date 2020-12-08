package com.lx.eduService.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lx.commonutils.R;
import com.lx.commonutils.ordervo.OrderCourseVo;
import com.lx.eduService.entity.EduCourse;
import com.lx.eduService.entity.EduTeacher;
import com.lx.eduService.entity.chapter.ChapterVo;
import com.lx.eduService.entity.frontvo.BaseCourseInfo;
import com.lx.eduService.entity.frontvo.CourseFrontVo;
import com.lx.eduService.service.EduChapterService;
import com.lx.eduService.service.EduCourseService;
import com.lx.eduService.service.EduTeacherService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.awt.font.LineMetrics;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/eduService/courseFront")
public class CourseFrontController {

    @Autowired
    private EduChapterService chapterService;
    @Autowired
    private EduCourseService courseService;

    @PostMapping("/getFrontCourse/{cur}/{limit}")
    public R getFrontCourse(@PathVariable long cur, @PathVariable long limit,
                            @RequestBody(required = false) CourseFrontVo condition){
        Page<EduCourse> page = new Page<>(cur,limit);
        Map<String, Object> map = courseService.selectFrontCourse(page,condition);
        return R.ok().data(map);
    }

    @GetMapping("/getFrontCourseInfo/{courseId}")
    public R getCourseInfo(@PathVariable String courseId){
        BaseCourseInfo courseInfo = courseService.getBaseCourseInfo(courseId);

        List<ChapterVo> chapterVideo = chapterService.getChapterVideo(courseId);

        return R.ok().data("baseCoureInfo",courseInfo).data("chapterVideo",chapterVideo);
    }

    @GetMapping("/getOrderInfo/{courseId}")
    public OrderCourseVo getInfo(@PathVariable String courseId){
        OrderCourseVo orderCourseVo = new OrderCourseVo();
        BaseCourseInfo baseCourseInfo = courseService.getBaseCourseInfo(courseId);
        BeanUtils.copyProperties(baseCourseInfo,orderCourseVo);
        return orderCourseVo;
    }


}

package com.lx.eduService.controller;


import com.lx.commonutils.R;
import com.lx.eduService.entity.EduChapter;
import com.lx.eduService.entity.chapter.ChapterVo;
import com.lx.eduService.entity.vo.CourseInfoVo;
import com.lx.eduService.service.EduChapterService;
import com.lx.eduService.service.EduCourseService;
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
@RequestMapping("/eduService/educhapter")
public class EduChapterController {

    @Autowired
    private EduChapterService eduChapterService;


    @GetMapping("/getChapterVideo/{courseId}")
    public R getChapterVideo(@PathVariable String courseId){
        List<ChapterVo> list = eduChapterService.getChapterVideo(courseId);
        return R.ok().data("allChapterVideo",list);
    }
    @PostMapping("/addChapter")
    public R addChapter(@RequestBody EduChapter chapter) {
        boolean save = eduChapterService.save(chapter);
        return R.ok();
    }

    @GetMapping("/getChapterInfo/{chapterId}")
    public R getChapterInfo(@PathVariable String chapterId){
        EduChapter chapter = eduChapterService.getById(chapterId);
        return R.ok().data("chapter",chapter);
    }

    @PostMapping("/updateChapter")
    public R updateChapter(@RequestBody EduChapter chapter) {
        boolean save = eduChapterService.updateById(chapter);
        return R.ok();
    }

    @DeleteMapping("/deleteChapter/{chapterId}")
    public R deleteChapter(@PathVariable String chapterId){
        boolean res = eduChapterService.deleteChapter(chapterId);

        if (res){
            return R.ok();
        }else{
            return R.error();
        }
    }


}


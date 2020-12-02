package com.lx.eduService.controller;


import com.lx.commonutils.R;
import com.lx.eduService.entity.subject.OneSubject;
import com.lx.eduService.service.EduSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-11-11
 */
@CrossOrigin
@RestController
@RequestMapping("/eduService/subject")
public class EduSubjectController {
    @Autowired
    private EduSubjectService eduSubjectService;

    @PostMapping("/addSubject")
    public R add(MultipartFile file){
        eduSubjectService.saveSubject(file, eduSubjectService);
        return R.ok();
    }

    @GetMapping("/getAllSubject")
    public R getAllSubject(){

        List<OneSubject> res = eduSubjectService.getAllOneTwoSubject();

        return R.ok().data("list",res);
    }

}


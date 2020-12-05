package com.lx.eduService.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lx.commonutils.R;
import com.lx.eduService.entity.EduComment;
import com.lx.eduService.service.EduCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 评论 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-12-02
 */
@RestController
@RequestMapping("/eduService/edu-comment")
public class EduCommentController {

    @Autowired
    private EduCommentService commentService;

    @GetMapping("/getCommentsPage/{cur}/{limit}")
    public R getCommentsPage(@PathVariable long cur,
                             @PathVariable long limit){
        Page<EduComment> page = new Page<>(cur,limit);
        commentService.page(page,null);
        return R.ok().data("comments",page.getRecords()).data("total",page.getTotal());
    }
}


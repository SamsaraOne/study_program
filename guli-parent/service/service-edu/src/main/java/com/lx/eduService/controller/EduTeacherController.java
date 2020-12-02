package com.lx.eduService.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lx.commonutils.R;
import com.lx.eduService.entity.EduTeacher;
import com.lx.eduService.entity.vo.TeacherQuery;
import com.lx.eduService.service.EduTeacherService;
import com.lx.servicebase.exceptionhandler.TestException;
import com.mysql.cj.util.StringUtils;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-11-07
 */
@CrossOrigin
@RestController
@RequestMapping("/eduService/edu-teacher")
public class EduTeacherController {
    @Autowired
    private EduTeacherService eduTeacherService;

    @GetMapping("/getTeacher")
    public R getTeacher(){
        List<EduTeacher>list = eduTeacherService.list(null);
        return R.ok().data("items",list);
    }
    @DeleteMapping("/delete/{id}")
    public R deleteTeacher(@PathVariable("id") String id){
        boolean flag = eduTeacherService.removeById(id);
        if (flag){
            return R.ok();
        }
        return R.error();
    }

    @GetMapping("/pageTeacher/{cur}/{mount}")
    public R getpageTeacher(@PathVariable long cur, @PathVariable long mount){
        Page<EduTeacher> page = new Page<>(cur,mount);
        eduTeacherService.page(page,null);
        long total = page.getTotal();
        List<EduTeacher> records = page.getRecords();
        return R.ok().data("total",total).data("rows",records);
    }
    //条件组合查询加分页
    @PostMapping("/getTeacherCondition/{cur}/{mount}")
    public R getConditionTeacher(@PathVariable Long cur,
                                 @PathVariable Long mount,
                                 @RequestBody(required = false) TeacherQuery condition){
        Page<EduTeacher> page = new Page<>(cur,mount);
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        //多条件查询
        String name = condition.getName();
        Integer level = condition.getLevel();
        String begin = condition.getBegin();
        String end = condition.getEnd();
        if (!StringUtils.isNullOrEmpty(name)){
            wrapper.like("name",name);
        }
        if (level!=null&&(level==0||level==1)){
            wrapper.eq("level",level);
        }
        if (!StringUtils.isNullOrEmpty(begin)){
            wrapper.ge("gmt_create",begin);
        }
        if (!StringUtils.isNullOrEmpty(end)){
            wrapper.le("gmt_create",end);
        }
        wrapper.orderByDesc("gmt_create");
        eduTeacherService.page(page,wrapper);
        long total = page.getTotal();
        List<EduTeacher> records = page.getRecords();
        return R.ok().data("total",total).data("rows",records);
    }
    @PostMapping("/addTeacher")
    public R addTeacher(@RequestBody EduTeacher teacher){
        return eduTeacherService.save(teacher) ?R.ok():R.error();
    }

    @GetMapping("/getTeacher/{id}")
    public R getTeacherById(@PathVariable String id){
        EduTeacher teacher = eduTeacherService.getById(id);

        return R.ok().data("teacher",teacher);
    }

    @PutMapping("{id}")
    public R updateById(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable String id,
            @ApiParam(name = "teacher", value = "讲师对象", required = true)
            @RequestBody EduTeacher teacher){
        teacher.setId(id);
        eduTeacherService.updateById(teacher);
        return R.ok();
    }
    @PostMapping("/updateTeacher")
    public R updateTeacher(@RequestBody EduTeacher teacher){
        boolean flag = eduTeacherService.updateById(teacher);
        if (flag){
            return R.ok();
        }else{
            return R.error();
        }
    }



}


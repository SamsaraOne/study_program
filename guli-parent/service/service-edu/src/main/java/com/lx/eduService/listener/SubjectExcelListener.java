package com.lx.eduService.listener;


import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lx.eduService.entity.EduSubject;
import com.lx.eduService.entity.excel.ExcelEntity;
import com.lx.eduService.service.EduSubjectService;
import com.lx.servicebase.exceptionhandler.TestException;

import java.util.Map;


public class SubjectExcelListener extends AnalysisEventListener<ExcelEntity> {

    private EduSubjectService eduSubjectService;

    public SubjectExcelListener(EduSubjectService eduSubjectService) {
        this.eduSubjectService = eduSubjectService;
    }

    public SubjectExcelListener() {
    }

    public void invoke(ExcelEntity excelEntity, AnalysisContext analysisContext) {
        if(excelEntity==null){
            throw new TestException(20001,"excel数据为空");
        }
        EduSubject existOneSubject = this.existOneSubject(excelEntity.getOneSubjectName(), eduSubjectService);
        if (existOneSubject==null){
            existOneSubject = new EduSubject();
            existOneSubject.setParentId("0");
            existOneSubject.setTitle(excelEntity.getOneSubjectName());
            eduSubjectService.save(existOneSubject);
        }

        String pid = existOneSubject.getId();
        EduSubject existTwoSubject = this.existTwoSubject(excelEntity.getTwoSubjectName(), eduSubjectService, pid);
        if (existTwoSubject==null){
            existTwoSubject = new EduSubject();
            existTwoSubject.setParentId(pid);
            existTwoSubject.setTitle(excelEntity.getTwoSubjectName());
            eduSubjectService.save(existTwoSubject);
        }

    }
    //判断一级分类不能重复添加
    public EduSubject existOneSubject(String name, EduSubjectService eduSubjectService){
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id",0);
        EduSubject one = eduSubjectService.getOne(wrapper);
        return one;
    }
    //判断二级分类不能重复添加
    public EduSubject existTwoSubject(String name, EduSubjectService eduSubjectService,String pid){
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id",pid);

        EduSubject two = eduSubjectService.getOne(wrapper);
        return two;
    }


    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}

package com.lx.eduService.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lx.eduService.entity.EduSubject;
import com.lx.eduService.entity.excel.ExcelEntity;
import com.lx.eduService.entity.subject.OneSubject;
import com.lx.eduService.entity.subject.TwoSubject;
import com.lx.eduService.listener.SubjectExcelListener;
import com.lx.eduService.mapper.EduSubjectMapper;
import com.lx.eduService.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-11-11
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    @Override
    public void saveSubject(MultipartFile file, EduSubjectService eduSubjectService) {
        try {
            InputStream in = file.getInputStream();
            EasyExcel.read(in, ExcelEntity.class, new SubjectExcelListener(eduSubjectService)).sheet().doRead();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public List<OneSubject> getAllOneTwoSubject() {

        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id","0");
        List<EduSubject> oneSubjectList = baseMapper.selectList(wrapper);

        QueryWrapper<EduSubject> wrapper2 = new QueryWrapper<>();
        wrapper.ne("parent_id","0");
        List<EduSubject> twoSubjectList = baseMapper.selectList(wrapper2);

        List<OneSubject> res = new ArrayList<>();
        for (EduSubject oneEdu:oneSubjectList){
            OneSubject oneSubject = new OneSubject();
            BeanUtils.copyProperties(oneEdu,oneSubject);
            List<TwoSubject> children = new ArrayList<>();
            for (EduSubject twoedu:twoSubjectList){

                if (oneEdu.getId().equals(twoedu.getParentId())){
                    TwoSubject twoSubject = new TwoSubject();
                    BeanUtils.copyProperties(twoedu,twoSubject);
                    children.add(twoSubject);
                }

            }
            oneSubject.setChildren(children);
            res.add(oneSubject);
        }
        return res;



    }
}

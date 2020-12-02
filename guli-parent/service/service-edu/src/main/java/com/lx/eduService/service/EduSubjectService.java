package com.lx.eduService.service;

import com.lx.eduService.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lx.eduService.entity.subject.OneSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-11-11
 */
public interface EduSubjectService extends IService<EduSubject> {

    void saveSubject(MultipartFile file, EduSubjectService eduSubjectService);

    List<OneSubject> getAllOneTwoSubject();
}

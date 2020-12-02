package com.lx.eduService.service;

import com.lx.eduService.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lx.eduService.entity.chapter.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-11-12
 */
public interface EduChapterService extends IService<EduChapter> {

    List<ChapterVo> getChapterVideo(String coureId);

    boolean deleteChapter(String chapterId);

    void removeByCourseId(String courseId);
}

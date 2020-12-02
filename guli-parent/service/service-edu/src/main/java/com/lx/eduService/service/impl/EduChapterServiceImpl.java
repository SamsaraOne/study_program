package com.lx.eduService.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lx.eduService.client.VodClient;
import com.lx.eduService.entity.EduChapter;
import com.lx.eduService.entity.EduVideo;
import com.lx.eduService.entity.chapter.ChapterVo;
import com.lx.eduService.entity.chapter.VideoVo;
import com.lx.eduService.mapper.EduChapterMapper;
import com.lx.eduService.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lx.eduService.service.EduVideoService;
import com.lx.servicebase.exceptionhandler.TestException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-11-12
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {
     @Autowired
     private EduVideoService videoService;


    @Override
    public List<ChapterVo> getChapterVideo(String coureId) {
        QueryWrapper<EduChapter> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",coureId);
        List<EduChapter> chapterList = this.list(wrapper);

        QueryWrapper<EduVideo> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("course_id",coureId);
        List<EduVideo> videoList = videoService.list(wrapper1);

        List<ChapterVo> res = new ArrayList<>();
        for (EduChapter chapter: chapterList){
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(chapter,chapterVo);
            List<VideoVo> child = new ArrayList<>();
            for (EduVideo video:videoList){
                if (chapter.getId().equals(video.getChapterId())){
                    VideoVo videoVo = new VideoVo();
                    BeanUtils.copyProperties(video,videoVo);
                    child.add(videoVo);
                }
            }
            chapterVo.setChildren(child);
            res.add(chapterVo);

        }
        return res;




    }

    @Override
    public boolean deleteChapter(String chapterId) {

        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("chapter_id",chapterId);
        int count = videoService.count(wrapper);
        if (count>0){
            throw new TestException(20001,"无法删除");
        }else{
            int i = baseMapper.deleteById(chapterId);
            return i>0;
        }
    }

    @Override
    public void removeByCourseId(String courseId) {
        QueryWrapper<EduChapter> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        boolean remove = this.remove(wrapper);
    }
}

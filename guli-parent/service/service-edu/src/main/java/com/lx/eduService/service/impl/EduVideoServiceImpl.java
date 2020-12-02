package com.lx.eduService.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lx.eduService.client.VodClient;
import com.lx.eduService.entity.EduVideo;
import com.lx.eduService.mapper.EduVideoMapper;
import com.lx.eduService.service.EduVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mysql.cj.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-11-12
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {

    @Autowired
    private VodClient vodClient;

    @Override
    // TODO: 2020/11/23  删除小节的时候同时删除视频
    public void removeByCourseId(String id) {
        List<String> videoIds = new ArrayList<>();
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",id);
        List<EduVideo> videoList = baseMapper.selectList(wrapper);
        for (EduVideo cur:videoList){
            if(!StringUtils.isNullOrEmpty(cur.getVideoSourceId())){
                videoIds.add(cur.getVideoSourceId());
            }
        }
        if(videoIds.size()>0){
            vodClient.deleteBatch(videoIds);
        }
        int delete = baseMapper.delete(wrapper);
    }
}

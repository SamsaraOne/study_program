package com.lx.eduService.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lx.eduService.entity.EduTeacher;
import com.lx.eduService.mapper.EduTeacherMapper;
import com.lx.eduService.service.EduTeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-11-07
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {

    @Override
    public Map<String, Object> getListPageTeacher(Page<EduTeacher> page) {
        baseMapper.selectPage(page,null);
        Map<String, Object> map = new HashMap<>();
        List<EduTeacher> records = page.getRecords();
        long current = page.getCurrent();
        long pages = page.getPages();
        long size = page.getSize();
        long total = page.getTotal();
        boolean hasNext = page.hasNext();
        boolean hasPrevious = page.hasPrevious();

        map.put("items", records);
        map.put("current", current);
        map.put("pages", pages);
        map.put("size", size);
        map.put("total", total);
        map.put("hasNext", hasNext);
        map.put("hasPrevious", hasPrevious);
        return map;
    }
}

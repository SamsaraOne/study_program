package com.lx.eduService.controller;


import com.lx.commonutils.R;
import com.lx.eduService.client.VodClient;
import com.lx.eduService.entity.EduVideo;
import com.lx.eduService.service.EduVideoService;
import com.mysql.cj.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-11-12
 */
@RestController
@RequestMapping("/eduService/video")
@CrossOrigin
public class EduVideoController {

    @Autowired
    EduVideoService videoService;
    @Autowired
    private VodClient vodClient;
    //添加
    @PostMapping("/addVideo")
    public R getVideo(@RequestBody EduVideo video){
        boolean save = videoService.save(video);
        return R.ok();
    }
    //删除
    @DeleteMapping("/deleteVideo/{id}")
    public R deleteVideo(@PathVariable String id){
        String videoId = null;
        EduVideo video = videoService.getById(id);
        videoId = video.getVideoSourceId();
        if (!StringUtils.isNullOrEmpty(videoId)){
            vodClient.deleteVideo(videoId);
        }

        boolean b = videoService.removeById(id);

        return R.ok();
    }
    //修改(先查再改)
    @GetMapping("/getVideo/{id}")
    public R getVideo(@PathVariable String id){
        EduVideo res = videoService.getById(id);
        return R.ok().data("video",res);
    }

    //修改
    @PostMapping("/updateVideo")
    public R updateVideo(@RequestBody EduVideo video){
        boolean res = videoService.updateById(video);
        return R.ok();
    }
}


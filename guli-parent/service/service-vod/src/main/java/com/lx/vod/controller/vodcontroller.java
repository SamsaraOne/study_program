package com.lx.vod.controller;

import com.lx.commonutils.R;
import com.lx.vod.service.VodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/vod")
public class vodcontroller {
    @Autowired
    VodService vodservice;
    @PostMapping("/uploadVideo")
    public R updateVideo(MultipartFile file){
        String videoId = vodservice.uploadVideo(file);
        return R.ok().data("videoId",videoId);
    }

    @DeleteMapping("/deleteVideo/{videoId}")
    public R deleteVideo(@PathVariable String videoId){
        vodservice.deleteVideoById(videoId);
        return R.ok();
    }
    @DeleteMapping("/delete-batch")
    public R deleteBatch(@RequestParam("VidioList") List<String> VideoList){
        vodservice.removeBatchVideo(VideoList);
        return R.ok();
    }
}

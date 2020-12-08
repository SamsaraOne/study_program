package com.lx.eduucenter.controller;


import com.lx.commonutils.JwtUtils;
import com.lx.commonutils.R;
import com.lx.commonutils.ordervo.UcenterMemberVo;
import com.lx.eduucenter.entity.UcenterMember;
import com.lx.eduucenter.entity.vo.LoginVo;
import com.lx.eduucenter.entity.vo.RegisterVo;
import com.lx.eduucenter.service.UcenterMemberService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-11-28
 */
@RestController
@CrossOrigin
@RequestMapping("/eduucenter")
public class UcenterMemberController {
    @Autowired
    private UcenterMemberService centerService;

    @PostMapping("/login")
    public R login(@RequestBody LoginVo loginVo){
        String token = centerService.login(loginVo);
        return R.ok().data("token",token);
    }

    @PostMapping("/register")
    public R register(@RequestBody RegisterVo registerVo){
        centerService.register(registerVo);
        return R.ok();
    }
    //根据token获取用户信息
    @GetMapping("/getMemberInfo")
    public R getMemberInfo(HttpServletRequest request){
        String memberId= JwtUtils.getMemberIdByJwtToken(request);
        UcenterMember userInfo = centerService.getById(memberId);
        return R.ok().data("userInfo",userInfo);
    }
    //order的远程调用
    @PostMapping("/getUserInfo/{uid}")
    public UcenterMemberVo getRomoteInfo(@PathVariable String uid){
        UcenterMember ucenterMember = centerService.getById(uid);
        UcenterMemberVo ucenterMemberVo = new UcenterMemberVo();
        BeanUtils.copyProperties(ucenterMember,ucenterMemberVo);
        return ucenterMemberVo;
    }
}


package com.lx.eduucenter.controller;


import com.google.gson.Gson;

import com.lx.commonutils.JwtUtils;
import com.lx.eduucenter.entity.UcenterMember;
import com.lx.eduucenter.service.UcenterMemberService;
import com.lx.eduucenter.util.HttpClientUtils;
import com.lx.servicebase.exceptionhandler.TestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;



import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/api/ucenter/wx")
@CrossOrigin
public class WxApiController {

    @Autowired
    private UcenterMemberService memberService;
    @GetMapping("/login")
    public String getWxCode(){
        // String url = "http://localhost:8201/api/ucenter/wx/login?appid=wxed9954c01bb89b47&" +
        //         "response_type=code&scope=snsapi_login&redirect_uri=";
        String url = "https://open.weixin.qq.com/connect/qrconnect" +
                "?appid=%s" +
                "&redirect_uri=%s" +
                "&response_type=code" +
                "&scope=snsapi_login" +
                "&state=%s" +
                "#wechat_redirect";
        String encode_url = null;
        try {
            encode_url = URLEncoder.encode("http://guli.shop/api/ucenter/wx/callback","UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String format = String.format(url, "wxed9954c01bb89b47", encode_url, "samsara");
        return "redirect:"+format;
    }

    @GetMapping("/callback")
    public String callback(String code, String state){
        String baseAccessTokenUrl =
                "https://api.weixin.qq.com/sns/oauth2/access_token" +"?appid=%s" +
                        "&secret=%s" +
                        "&code=%s" +
                        "&grant_type=authorization_code";
        String url = String.format(baseAccessTokenUrl, "wxed9954c01bb89b47", "a7482517235173ddb4083788de60b90e", code);
        try {
            String Access_token_info = HttpClientUtils.get(url);
            //从Access_token_info中获取token和openid
            Gson gson = new Gson();
            HashMap Access_map = gson.fromJson(Access_token_info, HashMap.class);
            String access_token = (String) Access_map.get("access_token");
            String open_id = (String) Access_map.get("openid");
            UcenterMember member = memberService.selectByOpenId(open_id);
            //数据库中不存在openid的信息的时候才去获取信息
            if (member==null){
                //根据access_token和openid继续请求地址获取扫码人信息
                String baseUserInfoUrl = "https://api.weixin.qq.com/sns/userinfo" +
                        "?access_token=%s" +
                        "&openid=%s";
                String userInfoUrl = String.format(baseUserInfoUrl, access_token, open_id);
                String userInfo = HttpClientUtils.get(userInfoUrl);
                HashMap userInfo_map = gson.fromJson(userInfo, HashMap.class);
                String nickname = (String) userInfo_map.get("nickname");
                String headimgurl = (String) userInfo_map.get("headimgurl");
                member = new UcenterMember();
                member.setOpenid(open_id);
                member.setNickname(nickname);
                member.setAvatar(headimgurl);
                memberService.save(member);
            }
            String token = JwtUtils.getJwtToken(member.getId(),member.getNickname());
            return "redirect:http://localhost:3000?token="+ token;


        } catch (Exception e) {
            e.printStackTrace();
            throw new TestException(20001,"登陆失败");
        }


    }

}

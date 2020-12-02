package com.lx.vod.util;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.profile.DefaultProfile;

public class InitObject  {
    public static DefaultAcsClient initClient(String key, String pwd) throws Exception{
        String region = "cn-shanghai";
        DefaultProfile profile = DefaultProfile.getProfile(region, key, pwd);
        DefaultAcsClient client = new DefaultAcsClient(profile);
        return client;
    }
}

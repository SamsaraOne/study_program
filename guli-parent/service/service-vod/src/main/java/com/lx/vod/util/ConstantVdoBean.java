package com.lx.vod.util;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component

public class ConstantVdoBean implements InitializingBean {

    @Value("${keyid}")
    private String id;
    @Value("${keysecret}")
    private String pwd;

    public static String uid;
    public static String upwd;

    @Override
    public void afterPropertiesSet() throws Exception {
        uid = id;
        upwd = pwd;
    }
}

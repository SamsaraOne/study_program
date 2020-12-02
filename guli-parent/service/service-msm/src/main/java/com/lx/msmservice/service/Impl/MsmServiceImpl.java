package com.lx.msmservice.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.lx.msmservice.service.MsmService;
import com.lx.servicebase.exceptionhandler.TestException;
import com.mysql.cj.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
public class MsmServiceImpl implements MsmService {



    @Override
    public boolean sendMsm(String phone, Map<String, Object> param) {
        if(StringUtils.isNullOrEmpty(phone)||phone.length()!=11){
            throw new TestException(20001,"手机号不合法");
        }

        DefaultProfile profile =
                DefaultProfile.getProfile("default", "LTAI4G9byoGQBwPgmcTCTW9Q",
                        "rh9dOwIlD76k3A1CYGXIEbfewijOQD");
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        //request.setProtocol(ProtocolType.HTTPS);
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");

        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", "我的星空在线教育网站");
        request.putQueryParameter("TemplateCode", "SMS_205887166");
        request.putQueryParameter("TemplateParam",
                JSONObject.toJSONString(param));
        try {
            CommonResponse response = client.getCommonResponse(request);
            return response.getHttpResponse().isSuccess();
        } catch (ServerException e) {
            e.printStackTrace();
            return false;
        } catch (ClientException e) {
            e.printStackTrace();
            return false;
        }






    }
}

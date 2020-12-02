package com.lx.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.lx.oss.service.OssService;
import com.lx.oss.util.ConstantPropertiesUtil;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.InputStream;
import java.util.Date;
import java.util.UUID;

@Service
public class OssServiceImpl implements OssService {
    @Override
    public String uploadFileAvatar(MultipartFile file) {
        String endpoint = ConstantPropertiesUtil.END_POINT;
        // 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，
        // 创建并使用RAM子账号进行API访问或日常运维，
        // 请登录 https://ram.console.aliyun.com 创建。
        String accessKeyId = ConstantPropertiesUtil.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtil.ACCESS_KEY_SECRET;
        String bucketName = ConstantPropertiesUtil.BUCKET_NAME;

        try{
            // 创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

            // 上传文件流。
            InputStream inputStream = file.getInputStream();
            String filename  = file.getOriginalFilename();
            String uuid = UUID.randomUUID().toString();
            String upfilename = uuid+filename;
            String datepath = new DateTime().toString("yyyy/MM/dd");
            upfilename = datepath+"/"+upfilename;
            ossClient.putObject(bucketName, upfilename, inputStream);



            ossClient.shutdown();
            String url  = "https://"+bucketName+"."+endpoint+"/"+upfilename;

            return url;
        }catch (Exception e){
            e.printStackTrace();
            return  null;
        }

    }
}

package com.lx.commonutils;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class R {

    @ApiModelProperty("结果返回是否成功")
    private boolean success;

    @ApiModelProperty("响应码")
    private Integer code;
    private String message;
    private Map<String, Object> data = new HashMap<>();

    private R(){}
    public static R ok(){
        R  res = new R();
        res.setSuccess(true);
        res.setCode(ResultCode.SUCCESS);
        res.setMessage("成功");
        return res;
    }
    public static R error (){
        R  res = new R();
        res.setSuccess(false);
        res.setCode(ResultCode.FALSE);
        res.setMessage("失败");
        return res;
    }
    public R success(Boolean success){
        this.setSuccess(success);
        return this;
    }
    public R message(String message){
        this.setMessage(message);
        return this;
    }
    public R code(Integer code){
        this.setCode(code);
        return this;
    }
    public R data(String key, Object value){
        this.data.put(key, value);
        return this;
    }
    public R data(Map<String, Object> data){
        this.setData(data);
        return this;
    }

}

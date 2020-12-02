package com.lx.servicebase.exceptionhandler;
import com.lx.commonutils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R errorHandler(Exception ex){
        ex.printStackTrace();
        return R.error().message("全局统一异常处理");
    }
    @ExceptionHandler(TestException.class)
    @ResponseBody
    public R errorHandler(TestException ex){
        ex.printStackTrace();
        return R.error().message(ex.getMes()).code(ex.getCode());
    }



}

package com.lx.eduorder.client;


import com.lx.commonutils.ordervo.OrderCourseVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(name = "service-edu")
public interface CourseClient {

    @GetMapping("/eduService/courseFront/getOrderInfo/{courseId}")
    public OrderCourseVo getInfo(@PathVariable("courseId") String courseId);

}

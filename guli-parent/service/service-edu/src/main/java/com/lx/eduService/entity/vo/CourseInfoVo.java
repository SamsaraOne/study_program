package com.lx.eduService.entity.vo;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CourseInfoVo {



    private String Id;
    private String teacherId;
    private String subjectId;
    private String title;
    private BigDecimal price;
    private Integer lessonNum;
    private String cover;
    private String description;
    private String subjectParentId;
}

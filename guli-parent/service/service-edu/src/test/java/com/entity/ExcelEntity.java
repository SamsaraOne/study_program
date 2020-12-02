package com.entity;


import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class ExcelEntity {

    @ExcelProperty(value = "学生学号",index = 0)
    private Integer sno;

    @ExcelProperty(value = "学生姓名",index = 1)
    private String sname;
}

package com.lx.servicebase.exceptionhandler;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestException extends RuntimeException {
    private Integer code;
    private String mes;
}

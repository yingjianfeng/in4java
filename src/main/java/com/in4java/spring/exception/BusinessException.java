package com.in4java.spring.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class BusinessException extends RuntimeException {
    private Integer code;
    private String message;
}

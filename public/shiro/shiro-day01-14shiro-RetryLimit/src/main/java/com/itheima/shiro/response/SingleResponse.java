package com.itheima.shiro.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SingleResponse<T> implements Serializable {
    private T value;
    private String code;
    private String msg;
}

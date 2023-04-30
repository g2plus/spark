package com.itheima.shiro.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MultiResponse<T> implements Serializable {
    private List<T> values;
    private String code;
    private String msg;
}

package com.itheima.shiro.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResponse<T> implements Serializable {
    private List<T> values;
    private String code;
    private String msg;
    private Integer page;
    private Integer row;
    private Integer totalPage;
    private Integer totalRows;
}

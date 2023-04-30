package com.heima.model.common.dtos;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class LoginDto {

    /**
     * 请求登录用户的手机号
     */
    @ApiModelProperty(value="手机号",required = true)
    private String phone;

    /**
     * 请求登录用户输入的密码
     */
    @ApiModelProperty(value="密码",required = true)
    private String password;

}

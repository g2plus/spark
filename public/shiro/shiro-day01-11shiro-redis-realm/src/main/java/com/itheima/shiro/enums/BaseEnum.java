package com.itheima.shiro.enums;

public enum BaseEnum {

    FAIL("500", ""),
    SUCCESS("200","");

    private String code;
    private String desc;

    BaseEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return this.code;
    }

    public String getDesc() {
        return this.desc;
    }


}

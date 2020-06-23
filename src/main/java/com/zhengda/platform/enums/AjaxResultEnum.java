package com.zhengda.platform.enums;

/**
 * 用户状态
 *
 * @author wolf
 */
public enum AjaxResultEnum {
    SUCCESS(200, "成功"),
    PARAMETERS_ERROR(3000, "参数异常"),
    PARAMETERS_ERROR_NO_TOKEN(3001, "缺少accessToken"),
    ERROR(5000, "系统异常");

    private final Integer code;
    private final String msg;

    AjaxResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}

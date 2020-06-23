package com.zhengda.platform.domain;

import com.zhengda.platform.enums.AjaxResultEnum;
import org.springframework.util.StringUtils;

import java.util.HashMap;

/**
 * 操作消息提醒
 *
 * @author wolf
 */
public class AjaxResult extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    /**
     * 状态码
     */
    public static final String CODE_TAG = "code";

    /**
     * 返回内容
     */
    public static final String MSG_TAG = "msg";

    /**
     * 数据对象
     */
    public static final String DATA_TAG = "data";


    /**
     * 初始化一个新创建的 AjaxResult 对象，使其表示一个空消息。
     */
    public AjaxResult() {
    }

    /**
     * 初始化一个新创建的 AjaxResult 对象
     *
     * @param type 状态类型
     */
    public AjaxResult(AjaxResultEnum type) {
        super.put(CODE_TAG, type.getCode());
        super.put(MSG_TAG, type.getMsg());
    }

    /**
     * 初始化一个新创建的 AjaxResult 对象
     *
     * @param type 状态类型
     * @param data 数据对象
     */
    public AjaxResult(AjaxResultEnum type, Object data) {
        super.put(CODE_TAG, type.getCode());
        super.put(MSG_TAG, type.getMsg());
        if (!StringUtils.isEmpty(data)) {
            super.put(DATA_TAG, data);
        }
    }

    /**
     * 返回成功消息
     *
     * @param data 数据对象
     * @return 成功消息
     */
    public static AjaxResult success(Object data) {
        return new AjaxResult(AjaxResultEnum.SUCCESS, data);
    }

    /**
     * 返回失败消息
     *
     * @param msg 数据对象
     * @return 失败消息
     */
    public static AjaxResult error(String msg) {
        return new AjaxResult(AjaxResultEnum.ERROR.getCode(), msg, null);
    }


    /**
     * 警告消息
     *
     * @param msg 数据对象
     * @return 警告消息
     */
    public static AjaxResult warn(String msg) {
        return new AjaxResult(AjaxResultEnum.PARAMETERS_ERROR.getCode(), msg, null);
    }


    public AjaxResult(Integer code, String msg, Object data) {
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);
        super.put(DATA_TAG, data);
    }


}

package com.get.domain.res;

import com.google.gson.Gson;

import java.util.HashMap;

public class AjaxResult extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    /**
     * 状态码
     */
    private static final String CODE_TAG = "code";

    /**
     * 返回内容
     */
    private static final String MSG_TAG = "msg";

    /**
     * 数据对象
     */
    private static final String DATA_TAG = "data";

    /**
     * 初始化一个新创建的 AjaxResult 对象，使其表示一个空消息。
     */
    public AjaxResult() {
    }

    /**
     * 初始化一个新创建的 AjaxResult 对象
     *
     * @param msg 异常信息
     */
    public AjaxResult(ExceptionMsg msg) {
        super.put(CODE_TAG, msg.getCode());
        super.put(MSG_TAG, msg.getMsg());
    }

    /**
     * 初始化一个新创建的 AjaxResult 对象
     *
     * @param msg  异常信息
     * @param data 数据对象
     */
    public AjaxResult(ExceptionMsg msg, Object data) {
        super.put(CODE_TAG, msg.getCode());
        super.put(MSG_TAG, msg.getMsg());
        if (data != null) {
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
        return new AjaxResult(ExceptionMsg.SUCCESS, data);
    }


    /**
     * 返回成功消息
     *
     * @return 成功消息
     */
    public static AjaxResult success() {
        return success(null);
    }

    /**
     * 返回识别信息
     *
     * @param msg 信息
     * @return 失败信息
     */
    public static AjaxResult fail(ExceptionMsg msg) {
        return new AjaxResult(msg);
    }

    public static AjaxResult error() {
        return new AjaxResult(ExceptionMsg.ERROR);
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}

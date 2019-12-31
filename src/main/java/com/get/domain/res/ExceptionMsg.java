package com.get.domain.res;

public enum ExceptionMsg {
    SUCCESS("000000", "操作成功"),
    ParamError("000001", "参数错误！"),
    ERROR("55555", "出现异常，请刷新页面后重试"),
    FAILED("999999", "操作失败"),

    LoginEmailNotRegister("000103", "该邮箱地址未注册"),
    LoginEmailOrPasswordError("000100", "邮箱或密码错误！"),
    EmailUsed("000101", "该邮箱已被注册"),
    NameUsed("000102", "该登录名称已存在"),
    UserNameSame("000108", "新用户名与原用户名一致"),

    LinkOutdated("000104", "该链接已过期，请重新请求"),
    PassWordError("000105", "密码输入错误"),
    UserNameLengthLimit("000106", "用户名长度超限"),
    LoginNameNotExists("000107", "该用户未注册"),


    FileEmpty("000400", "上传文件为空"),
    LimitPictureSize("000401", "图片大小必须小于2M"),
    LimitPictureType("000402", "图片格式必须为'jpg'、'png'、'jpge'、'gif'、'bmp'"),

    ThemeNameUsed("000500", "主题名字已被使用"),
    ThemeNotExist("000501", "主题不存在"),

    TaskNameUsed("000600", "任务名已被使用"),
    TaskNotExist("000601", "任务不存在"),
    NoTask("000602", "无任务");;

    private ExceptionMsg(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private String code;
    private String msg;

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}

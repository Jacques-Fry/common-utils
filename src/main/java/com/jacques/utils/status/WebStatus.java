package com.jacques.utils.status;

public enum WebStatus {
    /**
     * 成功
     */
    OK (200,"调用成功"),
    /**
     * 程序未知错误
     */
    ERROR (500,"程序未知错误"),
    /**
     * 重复操作
     */
    REPETITIVE_OPERATION (3003,"重复操作"),

    /**
     * 参数错误
     */
    PARAM_ERROR (4000,"参数错误"),
    /**
     * 数据不存在
     */
    NOT_EXISTS(4004,"数据不存在"),
    /**
     * 文件上传失败
     */
    FILE_UPLOAD_ERROR(4021,"文件上传失败"),

    /**
     * 远程调用失败
     */
    REMOTE_ERROR(5021,"远程调用失败"),

    /**
     * token过期或无效/未登录
     */
    TOKEN_ERROR(6000,"token过期或无效/未登录"),
    /**
     * 用户名或密码错误
     */
    USER_ERROR(6001,"用户名或密码错误"),
    /**
     * 账号被冻结
     */
    FREEZE(6002,"账号被冻结"),
    /**
     * 没有权限
     */
    SYS_ERROR(6003,"没有权限");

    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    WebStatus(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}

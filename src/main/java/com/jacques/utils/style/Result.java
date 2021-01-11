package com.jacques.utils.style;

import com.jacques.utils.status.WebStatus;

import java.io.Serializable;

/**
 * JSON返回格式
 *
 * @info JSON返回格式
 * @version 1.0.0
 * @author Jacques·Fry
 * @since 2021/1/4 17:56
 */
public class Result<T> implements Serializable {

    private boolean flag;
    private Integer code;
    private String msg;
    private T data;


    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Result(WebStatus webStatus) {
        super();
        this.flag = false;
        this.code = webStatus.getCode();
        this.msg = webStatus.getMsg();
    }

    public Result() {
        this.flag = true;
        this.code = WebStatus.OK.getCode();
        this.msg = WebStatus.OK.getMsg();
    }

    public Result(String msg) {
        this.flag = true;
        this.code = WebStatus.OK.getCode();
        this.msg =msg;
    }

    public Result(String msg, T data) {
        this.flag = true;
        this.code = WebStatus.OK.getCode();
        this.msg = msg;
        this.data=data;
    }

    public Result(WebStatus webStatus, String msg) {
        this.flag = false;
        this.code = webStatus.getCode();
        this.msg = msg;
    }

    public Result(boolean flag, Integer code, String msg) {
        this.flag = flag;
        this.code =code;
        this.msg = msg;
    }
}

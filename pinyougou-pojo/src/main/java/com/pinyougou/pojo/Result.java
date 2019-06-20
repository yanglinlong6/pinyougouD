package com.pinyougou.pojo;

import java.io.Serializable;

/**
 * 项目名:pinyougouDemo
 * 包名: com.pinyougou.pojo
 * 作者: Yanglinlong
 * 日期: 2019/6/19 11:16
 */
public class Result implements Serializable {
    private boolean success;
    private String message;

    public Result(boolean success, String message) {
        super();
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

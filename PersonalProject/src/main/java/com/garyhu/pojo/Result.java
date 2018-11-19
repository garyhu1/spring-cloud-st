package com.garyhu.pojo;

import lombok.Data;

/**
 * @author: garyhu
 * @since: 2018/10/26 0026
 * @decription:
 */
@Data
public class Result<T> {
    private int code;
    private String message;
    private T result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}

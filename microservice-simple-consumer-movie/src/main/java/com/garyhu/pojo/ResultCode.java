package com.garyhu.pojo;

/**
 * @author: garyhu
 * @since: 2018/10/26 0026
 * @decription:
 */
public enum ResultCode {

    SUCCESS(0, "请求成功"),
    WEAK_NET_WORK(-1, "网络异常，请稍后重试"),
    USERNAME_NULL(1000,"用户名为空"),
    PASSWORD_NULL(1001,"密码为空"),
    SUREWORD_NULL(1002,"确认密码为空"),
    PASSWORD_NOMATCH(1003,"两次输入的密码不一致"),
    PASSWORD_ERROR(10001, "用户名或密码错误"),
    PARAMETER_ERROR(10101, "参数错误");

    private int code;
    private String msg;

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}

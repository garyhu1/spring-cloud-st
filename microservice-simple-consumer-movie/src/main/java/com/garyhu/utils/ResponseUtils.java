package com.garyhu.utils;

import com.garyhu.pojo.Result;

/**
 * @author: garyhu
 * @since: 2018/10/26 0026
 * @decription:
 */
public class ResponseUtils {

    public static Result success(Object o){
        int code  = o == null ? 300 : 200;
        String message = o == null ? "数据请求失败":"请求成功";
        Result result = new Result();
        result.setCode(code);
        result.setMessage(message);
        result.setResult(o);
        return result;
    }

    public static Result warn(String message,int code){
        Result result = new Result();
        result.setCode(code);
        result.setMessage(message);
        result.setResult(null);
        return result;
    }
}

package com.garyhu.pojo;

/**
 * @author: garyhu
 * @since: 2018/11/13 0013
 * @decription:
 */
public class BaseResponse {

    private Object content;

    public BaseResponse(Object content){
        this.content = content;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}

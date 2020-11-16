package com.to.jing.course.sdk.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * 定义接口返回的数据格式 <br/>
 * 主要包括 code msg data
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {
    private Integer code;
    private String msg;
    private Object data;

    public static Response SUCCESS(Object data){
        return new Response(0,"success",data);
    }

    public static Response SUCCESS(){
        Response response = new Response();
        response.setCode(0);
        response.setMsg("success");
        return response;
    }

    public void failed(String msg){
        this.code = -1;
        this.msg = msg;
        this.data = null;
    }
}

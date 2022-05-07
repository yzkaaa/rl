package com.yzk.sys.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 承担着返回首页文章列表的类
 *     "success": true,
 *     "code": 200,
 *     "msg": "success",
 *     "data": []
 *
 *   success为中表示是否成功
 *   code为状态码、msg为提示信息、data为返回的数据
 *
 */
@Data
@AllArgsConstructor
public class Result {

    //代表是否成功
    private boolean success;

    //代表我们的编码
    private int code;

    //代表的是消息
    private String msg;

    //代表的是数据
    private Object data;

   //成功
    public static Result success(Object data){
        return new Result(true,200,"success",data);
    }
   //失败
    public static Result fail(int code, String msg){
        return new Result(false,code,msg,null);
    }
}

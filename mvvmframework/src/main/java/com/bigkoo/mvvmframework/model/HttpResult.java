package com.bigkoo.mvvmframework.model;

/**
 * Created by Sai on 16/6/1.
 */
public class HttpResult<T>  {

    /**
     * 默认约定返回 格式 ： {"status":0,"msg":"提示消息","content":{}}
     * status : 0
     * msg : 提示消息
     * content : {} 或 {[{},{},{}]}
     */


    private boolean code;

    private T results;
//    private String msg;

    public void setCode(boolean code) {
        this.code = code;
    }

    public void setContent(T content) {
        this.results = content;
    }

//    public void setMsg(String msg) {
//        this.msg = msg;
//    }

    public boolean getCode() {
        return code;
    }

    public T getContent() {
        return results;
    }

//    public String getMsg() {
//        return msg;
//    }
}

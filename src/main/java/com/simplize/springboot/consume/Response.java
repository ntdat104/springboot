package com.simplize.springboot.consume;

public class Response {
    private int code;
    private String message;
    private Object data;

    public Response(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    public Object getData() {
        return this.data;
    }
}

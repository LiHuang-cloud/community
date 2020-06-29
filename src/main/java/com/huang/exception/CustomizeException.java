package com.huang.exception;




public class CustomizeException extends RuntimeException {
    private String message;

    private Integer code;
    @Override
    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }

    public CustomizeException(ICustomizeErrorCode iCustomizeErrorCode){
        this.code=iCustomizeErrorCode.getCode();
        this.message=iCustomizeErrorCode.getMessage();
    }
}

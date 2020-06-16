package com.huang.exception;



public class CustomizeException extends RuntimeException {
    private String message;

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public CustomizeException(String message){
        this.message=message;
    }
    public CustomizeException(ICustomizeErrorCode iCustomizeErrorCode){
        this.message=iCustomizeErrorCode.getMessage();
    }
}

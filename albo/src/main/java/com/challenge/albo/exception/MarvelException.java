package com.challenge.albo.exception;

public class MarvelException extends Exception{
    private String message;
    private Integer status;

    public MarvelException(String message, Integer status) {
        super(message);
        this.status = status;
        this.message = message;
    }

    public  MarvelException(){
        super();
    }


    public Integer getStatus() {
        return status;
    }


    @Override
    public String getMessage() {
        return message;
    }
}

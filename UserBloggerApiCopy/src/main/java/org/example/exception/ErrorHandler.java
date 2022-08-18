package org.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ErrorHandler extends RuntimeException{
    private static final long serialVersionUID = 1L;

    Integer code;
    String message;

    public ErrorHandler(){

    }
    public ErrorHandler(Integer code,String Message){
        this.code=code;
        this.message = Message;

    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}

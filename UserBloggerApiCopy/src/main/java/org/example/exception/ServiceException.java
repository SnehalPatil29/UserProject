package org.example.exception;

import org.springframework.stereotype.Component;

@Component
public class ServiceException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    Integer code;
    String message;

    public ServiceException(){

    }

    public ServiceException(Integer code, String message) {
        this.code = code;
        this.message = message;
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

package org.example.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class UserBlogException {

    @ExceptionHandler(value = ErrorHandler.class)
    public ResponseEntity<Object> exception(ErrorHandler exception) {

        return new ResponseEntity<>("Data Not found",HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException e){
        return new ResponseEntity<>("No such user present",HttpStatus.NOT_FOUND);
    }

}

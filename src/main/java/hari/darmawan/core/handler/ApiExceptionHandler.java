package hari.darmawan.core.handler;

import hari.darmawan.core.exception.ApiException;
import hari.darmawan.core.exception.ApiRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(value = {ApiRequestException.class})
    public ResponseEntity<Object> handleApiRequestException (ApiRequestException e){
        // create payload containing exception details
       ApiException apiException = new ApiException(e.getMessage(),e, HttpStatus.BAD_REQUEST, ZonedDateTime.now());
        // return response entity
        return new ResponseEntity<>(apiException,HttpStatus.BAD_REQUEST);
    }
}

package com.example.ProgrammingTechnology.controler;

import com.example.ProgrammingTechnology.exception.AttributeException;
import com.example.ProgrammingTechnology.responses.AttrResponse;
import org.apache.logging.log4j.message.Message;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.w3c.dom.Attr;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value= {AttributeException.class})
    protected ResponseEntity<Object> handleConflict(
            AttributeException ex, WebRequest request) {
        return handleExceptionInternal(ex, AttrResponse.fromEx(ex),
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }



}

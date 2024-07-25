package com.codecool.backend.controller;

import com.codecool.backend.exception.MemberAlreadyExistsException;
import com.codecool.backend.exception.MemberIsAlreadyReportedException;
import com.codecool.backend.exception.MemberIsNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {
    @ResponseBody
    @ExceptionHandler(MemberAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String memberAlreadyExistsExceptionHandler(MemberAlreadyExistsException e) {
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(MemberIsNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String memberIsNotFoundExceptionHandler(MemberIsNotFoundException e) {
        return e.getMessage();
    }


    @ResponseBody
    @ExceptionHandler(MemberIsAlreadyReportedException.class)
    @ResponseStatus(HttpStatus.ALREADY_REPORTED)
    public String memberIsAlreadyReportedExceptionHandler(MemberIsAlreadyReportedException e) {
        return e.getMessage();
    }
}

package com.codecool.backend.exception;

public class MemberIsAlreadyReportedException extends RuntimeException {
    public MemberIsAlreadyReportedException(String message) {
        super(message);
    }
}

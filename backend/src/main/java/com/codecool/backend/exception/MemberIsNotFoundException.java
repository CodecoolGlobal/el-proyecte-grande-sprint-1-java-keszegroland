package com.codecool.backend.exception;

public class MemberIsNotFoundException extends RuntimeException {
    public MemberIsNotFoundException(String message) {
        super(message);
    }
}

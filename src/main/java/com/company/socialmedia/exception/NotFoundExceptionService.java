package com.company.socialmedia.exception;

public class NotFoundExceptionService extends RuntimeException {
    public NotFoundExceptionService(String message) {
        super(message);
    }

    public NotFoundExceptionService(String message, Throwable cause) {
        super(message, cause);
    }
}

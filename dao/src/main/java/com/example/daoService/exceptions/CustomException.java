package com.example.daoService.exceptions;

import org.springframework.http.HttpStatusCode;

public class CustomException extends RuntimeException {
    private static int status = 400;

    public CustomException(String message) {
        super(message);
        System.out.println("CustomException: " + message);
    }

    public CustomException() {
        super();
    }

    public CustomException(HttpStatusCode statusCode, String errorBody) {
        super(errorBody);
        status = statusCode.value();
    }

    public int getStatus() {
        return status;
    }
}

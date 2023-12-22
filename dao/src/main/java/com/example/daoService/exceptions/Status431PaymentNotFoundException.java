package com.example.daoService.exceptions;

public class Status431PaymentNotFoundException extends CustomException {
    private static final int status = 431;

    public Status431PaymentNotFoundException(String message) {
        super(message);
    }

    public Status431PaymentNotFoundException() {
        super("Payment not found");
    }

    public int getStatus() {
        return status;
    }
}

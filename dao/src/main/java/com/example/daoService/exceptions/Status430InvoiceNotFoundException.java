package com.example.daoService.exceptions;

public class Status430InvoiceNotFoundException extends CustomException{
    private static final int status = 430;

    public Status430InvoiceNotFoundException(String message) {
        super(message);
    }
    public Status430InvoiceNotFoundException() {
        super("Invoice not found");
    }

    public int getStatus() {
        return status;
    }
}

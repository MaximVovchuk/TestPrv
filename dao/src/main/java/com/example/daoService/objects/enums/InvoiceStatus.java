package com.example.daoService.objects.enums;

import lombok.Getter;
import lombok.Setter;

public enum InvoiceStatus {
    ACTIVE('A'), //money is not withdrawn
    STORMED('S'); //money is withdrawn
    @Getter
    private final char status;

    InvoiceStatus(char status) {
        this.status = status;
    }
}

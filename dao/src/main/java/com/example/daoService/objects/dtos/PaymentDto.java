package com.example.daoService.objects.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class PaymentDto {
    private String senderName;
    private Long inn;
    private Long cardNumber;
    private Long account;
    private String mfo;
    private Long okpo;
    private String getterName;
    private Integer period;
    private BigDecimal amount;
}

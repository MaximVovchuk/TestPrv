package com.example.daoService.objects.dtos;

import com.example.daoService.objects.entities.Payment;
import com.example.daoService.objects.enums.InvoiceStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
public class InvoiceDto {
    private LocalDateTime invoiceTime;
    private Long paymentId;
    private BigDecimal amount;
    private InvoiceStatus status;
}

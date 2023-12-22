package com.example.regpayment.dao;

import com.example.daoService.objects.entities.Invoice;
import com.example.daoService.objects.entities.Payment;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PaymentDao {
    Mono<Invoice> createInvoiceByPaymentId(Long paymentId);
    Flux<Payment> getAllPayments();
    Flux<Invoice> getPaymentHistory(Long paymentId);
}
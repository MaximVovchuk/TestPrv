package com.example.business.services;

import com.example.daoService.objects.dtos.PaymentDto;
import com.example.daoService.objects.entities.Invoice;
import com.example.daoService.objects.entities.Payment;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PaymentService {
    Mono<Payment> createPayment(PaymentDto paymentDto);

    Mono<Invoice> createInvoiceByPaymentId(Long paymentId);

    Flux<Payment> getAllPaymentsBySenderName(String senderName);

    Flux<Payment> getAllPaymentsByOkpo(Long okpo);

    Flux<Payment> getAllPayments();

    Flux<Invoice> getPaymentHistory(Long paymentId);
}

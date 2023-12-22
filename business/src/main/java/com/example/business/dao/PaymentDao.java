package com.example.business.dao;

import com.example.daoService.objects.dtos.PaymentDto;
import com.example.daoService.objects.entities.Payment;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PaymentDao {

    Mono<Payment> createPayment(PaymentDto paymentDto);

    Mono<Payment> updatePayment(PaymentDto paymentDto, Long paymentId);

    Mono<Void> deletePayment(Long id);

    Mono<Payment> getPayment(Long id);

    Flux<Payment> getAllPaymentsByInn(Long inn);

    Flux<Payment> getAllPaymentsByOkpo(Long okpo);

    Flux<Payment> getAllPaymentsBySenderName(String senderName);

    Flux<Payment> getAllPayments();
}

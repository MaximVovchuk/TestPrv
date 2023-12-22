package com.example.business.controllers;


import com.example.business.services.PaymentService;
import com.example.daoService.exceptions.Status431PaymentNotFoundException;
import com.example.daoService.objects.dtos.PaymentDto;
import com.example.daoService.objects.entities.Invoice;
import com.example.daoService.objects.entities.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("payment")
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping
    public Mono<Payment> createPayment(@RequestBody PaymentDto paymentDto) {
        return paymentService.createPayment(paymentDto);
    }

    @PostMapping("/invoice/{paymentId}")
    public Mono<Invoice> createInvoiceByPaymentId(@PathVariable Long paymentId) {
        return paymentService.createInvoiceByPaymentId(paymentId);
    }

    @GetMapping("/all")
    public Flux<Payment> getAllPayments() {
        return paymentService.getAllPayments();
    }

    @GetMapping("/senderName/{senderName}")
    public Flux<Payment> getAllPaymentsBySenderName(@PathVariable String senderName) throws Status431PaymentNotFoundException {
        return paymentService.getAllPaymentsBySenderName(senderName);
    }

    @GetMapping("/okpo/{okpo}")
    public Flux<Payment> getAllPaymentsByOkpo(@PathVariable Long okpo) throws Status431PaymentNotFoundException {
        return paymentService.getAllPaymentsByOkpo(okpo);
    }

    @GetMapping("/history/{paymentId}")
    public Flux<Invoice> getPaymentHistory(@PathVariable Long paymentId) {
        return paymentService.getPaymentHistory(paymentId);
    }
}

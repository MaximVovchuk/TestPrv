package com.example.daoService.controllers;

import com.example.daoService.exceptions.CustomException;
import com.example.daoService.objects.dtos.PaymentDto;
import com.example.daoService.objects.entities.Payment;
import com.example.daoService.services.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("payment")
public class PaymentController {
    private final PaymentService paymentService;

    @GetMapping("/{id}")
    public ResponseEntity<Payment> getPayment(@PathVariable Long id) throws CustomException {
        return ResponseEntity.ok(paymentService.getPayment(id));
    }

    @PostMapping()
    public ResponseEntity<Payment> createPayment(@RequestBody PaymentDto paymentDto) throws CustomException {
        return new ResponseEntity<>(paymentService.createPayment(paymentDto), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Payment> updatePayment(@RequestBody PaymentDto paymentDto, @PathVariable Long id) throws CustomException {
        return ResponseEntity.ok(paymentService.updatePayment(paymentDto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable Long id) throws CustomException {
        paymentService.deletePayment(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/inn/{inn}")
    public ResponseEntity<List<Payment>> getPaymentsByInn(@PathVariable Long inn) throws CustomException {
        return ResponseEntity.ok(paymentService.getAllPaymentsByInn(inn));
    }

    @GetMapping("/okpo/{okpo}")
    public ResponseEntity<List<Payment>> getPaymentsByOkpo(@PathVariable Long okpo) throws CustomException {
        return ResponseEntity.ok(paymentService.getAllPaymentsByOkpo(okpo));
    }

    @GetMapping("/senderName/{senderName}")
    public ResponseEntity<List<Payment>> getPaymentsBySenderName(@PathVariable String senderName) throws CustomException {
        return ResponseEntity.ok(paymentService.getAllPaymentsBySenderName(senderName));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Payment>> getAllPayments() throws CustomException {
        return ResponseEntity.ok(paymentService.getAllPayments());
    }
}

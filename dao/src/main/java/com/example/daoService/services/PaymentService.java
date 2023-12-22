package com.example.daoService.services;

import com.example.daoService.exceptions.CustomException;
import com.example.daoService.objects.dtos.PaymentDto;
import com.example.daoService.objects.entities.Payment;

import java.util.List;

public interface PaymentService {
    Payment createPayment(PaymentDto paymentDto) throws CustomException;

    Payment updatePayment(PaymentDto paymentDto, Long paymentId) throws CustomException;

    void deletePayment(Long id) throws CustomException;

    Payment getPayment(Long id) throws CustomException;

    List<Payment> getAllPaymentsByInn(Long inn) throws CustomException;

    List<Payment> getAllPaymentsByOkpo(Long okpo) throws CustomException;

    List<Payment> getAllPaymentsBySenderName(String senderName) throws CustomException;

    List<Payment> getAllPayments();
}

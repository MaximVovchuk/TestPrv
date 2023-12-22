package com.example.business.services.implementations;


import com.example.business.dao.InvoiceDao;
import com.example.business.dao.PaymentDao;
import com.example.business.services.PaymentService;
import com.example.business.util.PaymentValidator;
import com.example.daoService.objects.dtos.InvoiceDto;
import com.example.daoService.objects.dtos.PaymentDto;
import com.example.daoService.objects.entities.Invoice;
import com.example.daoService.objects.entities.Payment;
import com.example.daoService.objects.enums.InvoiceStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Comparator;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentServiceImpl implements PaymentService {
    private final PaymentDao paymentDao;
    private final InvoiceDao invoiceDao;
    private final PaymentValidator paymentValidator;


    @Override
    public Mono<Payment> createPayment(PaymentDto paymentDto) {
        paymentValidator.validate(paymentDto);
        log.info("PaymentServiceImpl creating payment: " + paymentDto);
        return paymentDao.createPayment(paymentDto);
    }

    @Override
    public Mono<Invoice> createInvoiceByPaymentId(Long paymentId) {
        return paymentDao.getPayment(paymentId)
                .flatMap(this::createInvoice);
    }

    private Mono<Invoice> createInvoice(Payment payment) {
        return getPaymentHistory(payment.getId())
                .filter(invoice -> invoice.getStatus().equals(InvoiceStatus.STORMED))
                .collectList()
                .flatMap(stormedInvoices -> {
                    Invoice lastInvoice = stormedInvoices.stream()
                            .max(Comparator.comparing(Invoice::getInvoiceTime))
                            .orElse(null);

                    InvoiceDto invoiceDto = InvoiceDto.builder()
                            .invoiceTime(LocalDateTime.now())
                            .paymentId(payment.getId())
                            .amount(payment.getAmount())
                            .build();

                    if (lastInvoice == null
                            || lastInvoice.getInvoiceTime().plusDays(payment.getPeriod()).isBefore(LocalDateTime.now())) {
                        invoiceDto.setStatus(InvoiceStatus.STORMED);
                    } else {
                        invoiceDto.setStatus(InvoiceStatus.ACTIVE);
                    }
                    log.info("PaymentServiceImpl creating invoice: " + invoiceDto);
                    return invoiceDao.createInvoice(invoiceDto);
                });
    }

    @Override
    public Flux<Payment> getAllPaymentsBySenderName(String senderName) {
        log.info("PaymentServiceImpl getting all payments by senderName: " + senderName);
        return paymentDao.getAllPaymentsBySenderName(senderName);
    }

    @Override
    public Flux<Payment> getAllPaymentsByOkpo(Long okpo) {
        log.info("PaymentServiceImpl getting all payments by okpo: " + okpo);
        return paymentDao.getAllPaymentsByOkpo(okpo);
    }

    @Override
    public Flux<Payment> getAllPayments() {
        log.info("PaymentServiceImpl getting all payments");
        return paymentDao.getAllPayments();
    }

    @Override
    public Flux<Invoice> getPaymentHistory(Long paymentId) {
        log.info("PaymentServiceImpl getting payment history by paymentId: " + paymentId);
        return invoiceDao.getAllInvoicesByPaymentId(paymentId);
    }
}
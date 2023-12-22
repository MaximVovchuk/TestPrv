package com.example.regpayment;

import com.example.daoService.objects.entities.Invoice;
import com.example.regpayment.dao.PaymentDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Comparator;

@Service
@RequiredArgsConstructor
@Slf4j
public class EntryPoint {
    private final PaymentDao paymentDao;

    @Scheduled(fixedRateString = "${scheduled.delay}")
    public void test() {
        log.info("Scheduled task started");
        paymentDao.getAllPayments()
                .flatMap(payment -> {
                    Invoice invoice = findLastInvoice(paymentDao.getPaymentHistory(payment.getId()));
                    return (invoice == null || invoice.getInvoiceTime().plusDays(payment.getPeriod()).isBefore(LocalDateTime.now()))
                            ? paymentDao.createInvoiceByPaymentId(payment.getId())
                            : Mono.empty();
                }).subscribe();
    }


    Invoice findLastInvoice(Flux<Invoice> invoices) {
        return invoices.toStream()
                .max(Comparator.comparing(Invoice::getInvoiceTime))
                .orElse(null);
    }
}

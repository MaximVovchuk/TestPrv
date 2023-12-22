package com.example.regpayment.dao.implementations;


import com.example.daoService.objects.entities.Invoice;
import com.example.daoService.objects.entities.Payment;
import com.example.regpayment.dao.PaymentDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentDaoImpl implements PaymentDao {
    private static final String BASE_URL = "http://localhost:8081/payment";
    private final WebClient webClient;

    @Override
    public Mono<Invoice> createInvoiceByPaymentId(Long paymentId) {
        log.info("PaymentDao creating invoice by paymentId: " + paymentId);
        return webClient.post()
                .uri(BASE_URL + "/invoice/" + paymentId)
                .retrieve()
                .bodyToMono(Invoice.class);
    }

    @Override
    public Flux<Payment> getAllPayments() {
        log.info("PaymentDao getting all payments");
        return webClient.get()
                .uri(BASE_URL + "/all")
                .retrieve()
                .bodyToFlux(Payment.class);
    }

    @Override
    public Flux<Invoice> getPaymentHistory(Long paymentId) {
        log.info("PaymentDao getting payment history by paymentId: " + paymentId);
        return webClient.get()
                .uri(BASE_URL + "/history/" + paymentId)
                .retrieve()
                .bodyToFlux(Invoice.class);
    }
}

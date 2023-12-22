package com.example.business.dao.implementations;

import com.example.business.dao.PaymentDao;
import com.example.daoService.objects.dtos.PaymentDto;
import com.example.daoService.objects.entities.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class PaymentDaoImpl implements PaymentDao {
    private static final String BASE_PAYMENT_URL = "http://localhost:8080/payment";
    private final WebClient webClient;

    @Override
    public Mono<Payment> createPayment(PaymentDto paymentDto) {
        return webClient.post()
                .uri(BASE_PAYMENT_URL)
                .body(Mono.just(paymentDto), PaymentDto.class)
                .retrieve()
                .bodyToMono(Payment.class);
    }

    @Override
    public Mono<Payment> updatePayment(PaymentDto paymentDto, Long paymentId) {
        return webClient.patch()
                .uri(BASE_PAYMENT_URL + "/" + paymentId)
                .body(Mono.just(paymentDto), PaymentDto.class)
                .retrieve()
                .bodyToMono(Payment.class);
    }

    @Override
    public Mono<Void> deletePayment(Long id) {
        return webClient.delete()
                .uri(BASE_PAYMENT_URL + "/" + id)
                .retrieve()
                .bodyToMono(Void.class);
    }

    @Override
    public Mono<Payment> getPayment(Long paymentId) {
        return webClient.get()
                .uri(BASE_PAYMENT_URL + "/" + paymentId)
                .retrieve()
                .bodyToMono(Payment.class);
    }

    @Override
    public Flux<Payment> getAllPaymentsByInn(Long inn) {
        return webClient.get()
                .uri(BASE_PAYMENT_URL + "/inn/" + inn)
                .retrieve()
                .bodyToFlux(Payment.class);
    }

    @Override
    public Flux<Payment> getAllPaymentsByOkpo(Long okpo) {
        return webClient.get()
                .uri(BASE_PAYMENT_URL + "/okpo/" + okpo)
                .retrieve()
                .bodyToFlux(Payment.class);
    }

    @Override
    public Flux<Payment> getAllPaymentsBySenderName(String senderName) {
        return webClient.get()
                .uri(BASE_PAYMENT_URL + "/senderName/" + senderName)
                .retrieve()
                .bodyToFlux(Payment.class);
    }

    @Override
    public Flux<Payment> getAllPayments() {
        return webClient.get()
                .uri(BASE_PAYMENT_URL + "/all")
                .retrieve()
                .bodyToFlux(Payment.class);
    }

}

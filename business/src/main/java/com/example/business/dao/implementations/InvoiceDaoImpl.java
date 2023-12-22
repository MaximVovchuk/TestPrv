package com.example.business.dao.implementations;

import com.example.business.dao.InvoiceDao;
import com.example.daoService.objects.dtos.InvoiceDto;
import com.example.daoService.objects.entities.Invoice;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class InvoiceDaoImpl implements InvoiceDao {
    private static final String BASE_INVOICE_URL = "http://localhost:8080/invoice";
    private final WebClient webClient;


    @Override
    public Mono<Invoice> createInvoice(InvoiceDto invoiceDto) {
        return webClient.post()
                .uri(BASE_INVOICE_URL)
                .body(Mono.just(invoiceDto), InvoiceDto.class)
                .retrieve()
                .bodyToMono(Invoice.class);
    }

    @Override
    public Mono<Invoice> updateInvoice(InvoiceDto invoiceDto, Long invoiceId) {
        return webClient.patch()
                .uri(BASE_INVOICE_URL + "/" + invoiceId)
                .body(Mono.just(invoiceDto), InvoiceDto.class)
                .retrieve()
                .bodyToMono(Invoice.class);
    }

    @Override
    public Mono<Void> deleteInvoice(Long id) {
        return webClient.delete()
                .uri(BASE_INVOICE_URL + "/" + id)
                .retrieve()
                .bodyToMono(Void.class);
    }

    @Override
    public Mono<Invoice> getInvoiceById(Long id) {
        return webClient.get()
                .uri(BASE_INVOICE_URL + "/" + id)
                .retrieve()
                .bodyToMono(Invoice.class);
    }

    @Override
    public Flux<Invoice> getAllInvoicesByPaymentId(Long paymentId) {
        return webClient.get()
                .uri(BASE_INVOICE_URL + "/all/" + paymentId)
                .retrieve()
                .bodyToFlux(Invoice.class);
    }
}

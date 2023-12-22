package com.example.business.dao;

import com.example.daoService.objects.dtos.InvoiceDto;
import com.example.daoService.objects.entities.Invoice;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface InvoiceDao {
    Mono<Invoice> createInvoice(InvoiceDto invoiceDto);

    Mono<Invoice> updateInvoice(InvoiceDto invoiceDto, Long invoiceId);

    Mono<Void> deleteInvoice(Long id);

    Mono<Invoice> getInvoiceById(Long id);

    Flux<Invoice> getAllInvoicesByPaymentId(Long paymentId);
}
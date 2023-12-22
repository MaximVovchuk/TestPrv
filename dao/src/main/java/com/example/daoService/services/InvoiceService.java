package com.example.daoService.services;

import com.example.daoService.exceptions.CustomException;
import com.example.daoService.objects.dtos.InvoiceDto;
import com.example.daoService.objects.entities.Invoice;
import com.example.daoService.objects.entities.Payment;

import java.util.List;

public interface InvoiceService {
    Invoice createInvoice(InvoiceDto invoiceDto) throws CustomException;

    Invoice updateInvoice(InvoiceDto invoiceDto, Long invoiceId) throws CustomException;

    void deleteInvoice(Long id) throws CustomException;

    Invoice getInvoiceById(Long id) throws CustomException;

    List<Invoice> getAllInvoicesByPaymentId(Long paymentId) throws CustomException;
}

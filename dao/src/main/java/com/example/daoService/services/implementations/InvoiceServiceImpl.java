package com.example.daoService.services.implementations;

import com.example.daoService.exceptions.CustomException;
import com.example.daoService.exceptions.Status430InvoiceNotFoundException;
import com.example.daoService.objects.dtos.InvoiceDto;
import com.example.daoService.objects.entities.Invoice;
import com.example.daoService.repositories.InvoiceRepository;
import com.example.daoService.services.InvoiceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class InvoiceServiceImpl implements InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final ModelMapper modelMapper;

    @Override
    public Invoice createInvoice(InvoiceDto invoiceDto) throws CustomException {
        if (invoiceDto == null) throw new CustomException("Didn`t receive invoiceDto");
        final Invoice mapped = modelMapper.map(invoiceDto, Invoice.class);
        mapped.setId(null);
        log.info("InvoiceServiceImpl creating invoice: " + mapped);
        return invoiceRepository.save(mapped);
    }

    @Override
    public Invoice updateInvoice(InvoiceDto invoiceDto, Long invoiceId) throws CustomException {
        if (invoiceDto == null) throw new CustomException("Didn`t receive invoice");
        if (!invoiceRepository.existsById(invoiceId)) throw new Status430InvoiceNotFoundException();
        Invoice invoice = modelMapper.map(invoiceDto, Invoice.class);
        invoice.setId(invoiceId);
        log.info("InvoiceServiceImpl updating invoice: " + invoice);
        return invoiceRepository.save(invoice);
    }

    @Override
    public void deleteInvoice(Long id) throws CustomException {
        if (id == null) throw new CustomException("Didn`t receive id");
        if (!invoiceRepository.existsById(id)) throw new Status430InvoiceNotFoundException();
        invoiceRepository.deleteById(id);
        log.info("InvoiceServiceImpl deleting invoice with id: " + id);
    }

    @Override
    public Invoice getInvoiceById(Long id) throws CustomException {
        if (id == null) throw new CustomException("Didn`t receive id");
        log.info("InvoiceServiceImpl getting invoice with id: " + id);
        return invoiceRepository.findById(id).orElseThrow(Status430InvoiceNotFoundException::new);
    }

    @Override
    public List<Invoice> getAllInvoicesByPaymentId(Long paymentId) throws CustomException {
        if (paymentId == null) throw new CustomException("Didn`t receive payment");
        log.info("InvoiceServiceImpl getting all invoices by paymentId: " + paymentId);
        return invoiceRepository.findAllByPayment_Id(paymentId);
    }
}

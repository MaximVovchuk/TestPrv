package com.example.daoService.controllers;

import com.example.daoService.exceptions.CustomException;
import com.example.daoService.objects.dtos.InvoiceDto;
import com.example.daoService.objects.entities.Invoice;
import com.example.daoService.services.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/invoice")
public class InvoiceController {
    private final InvoiceService invoiceService;

    @GetMapping("/{id}")
    public ResponseEntity<Invoice> getInvoice(@PathVariable Long id) throws CustomException {
        return ResponseEntity.ok(invoiceService.getInvoiceById(id));
    }

    @PostMapping()
    public ResponseEntity<Invoice> createInvoice(@RequestBody InvoiceDto invoiceDto) throws CustomException {
        return ResponseEntity.ok(invoiceService.createInvoice(invoiceDto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Invoice> updateInvoice(@RequestBody InvoiceDto invoiceDto, @PathVariable Long id) throws CustomException {
        return ResponseEntity.ok(invoiceService.updateInvoice(invoiceDto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInvoice(@PathVariable Long id) throws CustomException {
        invoiceService.deleteInvoice(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/all/{paymentId}")
    public ResponseEntity<List<Invoice>> getAllInvoices(@PathVariable Long paymentId) throws CustomException {
        return ResponseEntity.ok(invoiceService.getAllInvoicesByPaymentId(paymentId));
    }
}

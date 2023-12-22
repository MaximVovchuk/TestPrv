package com.example.daoService.repositories;

import com.example.daoService.objects.entities.Invoice;
import com.example.daoService.objects.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    List<Invoice> findAllByPayment_Id(Long paymentId);
}

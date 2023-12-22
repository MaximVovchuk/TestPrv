package com.example.daoService.repositories;

import com.example.daoService.objects.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findAllByInn(Long inn);
    List<Payment> findAllByOkpo(Long okpo);
    List<Payment> findAllBySenderName(String senderName);
}

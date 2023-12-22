package com.example.daoService.services.implementations;

import com.example.daoService.exceptions.CustomException;
import com.example.daoService.exceptions.Status431PaymentNotFoundException;
import com.example.daoService.objects.dtos.PaymentDto;
import com.example.daoService.objects.entities.Payment;
import com.example.daoService.repositories.PaymentRepository;
import com.example.daoService.services.PaymentService;
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
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final ModelMapper modelMapper;

    @Override
    public Payment createPayment(PaymentDto paymentDto) throws CustomException {
        if (paymentDto == null) throw new CustomException("Didn`t receive paymentDto");
        log.info("PaymentServiceImpl creating payment: " + paymentDto);
        return paymentRepository.save(modelMapper.map(paymentDto, Payment.class));
    }

    @Override
    public Payment updatePayment(PaymentDto paymentDto, Long paymentId) throws CustomException {
        if (paymentDto == null) throw new CustomException("Didn`t receive payment");
        if (!paymentRepository.existsById(paymentId)) throw new Status431PaymentNotFoundException();
        Payment payment = modelMapper.map(paymentDto, Payment.class);
        payment.setId(paymentId);
        log.info("PaymentServiceImpl updating payment: " + payment);
        return paymentRepository.save(payment);
    }

    @Override
    public void deletePayment(Long id) throws CustomException {
        if (id == null) throw new CustomException("Didn`t receive id");
        if (!paymentRepository.existsById(id)) throw new Status431PaymentNotFoundException();
        paymentRepository.deleteById(id);
        log.info("PaymentServiceImpl deleting payment with id: " + id);
    }

    @Override
    public Payment getPayment(Long id) throws CustomException {
        if (id == null) throw new CustomException("Didn`t receive id");
        log.info("PaymentServiceImpl getting payment with id: " + id);
        return paymentRepository.findById(id).orElseThrow(Status431PaymentNotFoundException::new);
    }

    @Override
    public List<Payment> getAllPaymentsByInn(Long inn) throws CustomException {
        if (inn == null) throw new CustomException("Didn`t receive inn");
        log.info("PaymentServiceImpl getting all payments by inn: " + inn);
        return paymentRepository.findAllByInn(inn);
    }

    @Override
    public List<Payment> getAllPaymentsByOkpo(Long okpo) throws CustomException {
        if (okpo == null) throw new CustomException("Didn`t receive okpo");
        log.info("PaymentServiceImpl getting all payments by okpo: " + okpo);
        return paymentRepository.findAllByOkpo(okpo);
    }

    @Override
    public List<Payment> getAllPaymentsBySenderName(String senderName) throws CustomException {
        if (senderName == null) throw new CustomException("Didn`t receive senderName");
        log.info("PaymentServiceImpl getting all payments by senderName: " + senderName);
        return paymentRepository.findAllBySenderName(senderName);
    }

    @Override
    public List<Payment> getAllPayments() {
        log.info("PaymentServiceImpl getting all payments");
        return paymentRepository.findAll();
    }
}

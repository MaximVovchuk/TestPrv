package com.example.regpayment;

import com.example.daoService.objects.entities.Invoice;
import com.example.daoService.objects.entities.Payment;
import com.example.regpayment.dao.PaymentDao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EntryPointTest {

    @Mock
    private PaymentDao paymentDao;

    @InjectMocks
    private EntryPoint entryPoint;


    @Test
    void testScheduledTask() {
        // Mock data
        Invoice invoice = new Invoice();
        invoice.setInvoiceTime(LocalDateTime.now().minusDays(7));

        when(paymentDao.getAllPayments()).thenReturn(Flux.just(createTestPayment()));
        when(paymentDao.getPaymentHistory(anyLong())).thenReturn(Flux.just(invoice));
        when(paymentDao.createInvoiceByPaymentId(anyLong())).thenReturn(Mono.empty());

        // Invoke the scheduled task
        entryPoint.test();

        // Verify interactions
        verify(paymentDao, times(1)).getAllPayments();
        verify(paymentDao, times(1)).getPaymentHistory(anyLong());
        verify(paymentDao, times(1)).createInvoiceByPaymentId(anyLong());
    }

    private Payment createTestPayment() {
        Payment payment = new Payment();
        payment.setId(1L);
        payment.setSenderName("John Doe");
        payment.setInn(1234567890L);
        payment.setCardNumber(9876543210123456L);
        payment.setAccount(1234567890123456L);
        payment.setMfo("ABC123");
        payment.setOkpo(98753210L);
        payment.setGetterName("Jane Doesdfa");
        payment.setPeriod(6);
        payment.setAmount(new BigDecimal("1000.5"));

        return payment;
    }

    @Test
    void testFindLastInvoice() {
        // Mock data
        Invoice invoice1 = new Invoice();
        invoice1.setInvoiceTime(LocalDateTime.now().minusDays(5));

        Invoice invoice2 = new Invoice();
        invoice2.setInvoiceTime(LocalDateTime.now().minusDays(3));

        List<Invoice> invoices = Arrays.asList(invoice1, invoice2);

        // Create a Flux from the list of Invoices
        Flux<Invoice> flux = Flux.fromIterable(invoices);

        // Invoke the findLastInvoice method
        Invoice result = entryPoint.findLastInvoice(flux);

        // Verify the result
        assertEquals(invoice2, result);
    }
}

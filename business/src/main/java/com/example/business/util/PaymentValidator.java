package com.example.business.util;

import com.example.daoService.objects.dtos.PaymentDto;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class PaymentValidator {

    public void validate(PaymentDto paymentDto) throws IllegalArgumentException {
        validateStringField(paymentDto.getSenderName(), "Sender Name");
        validateINNField(paymentDto.getInn());
        validateCardNumberField(paymentDto.getCardNumber());
        validatePositiveLongField(paymentDto.getAccount(), "Account");
        validateMFOField(paymentDto.getMfo());
        validateOKPOField(paymentDto.getOkpo());
        validateStringField(paymentDto.getGetterName(), "Getter Name");
        validatePositiveIntegerField(paymentDto.getPeriod(), "Period");
        validatePositiveBigDecimalField(paymentDto.getAmount(), "Amount");
    }

    private void validateStringField(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be null or empty");
        }
    }

    private void validatePositiveLongField(Long value, String fieldName) {
        if (value == null || value <= 0) {
            throw new IllegalArgumentException(fieldName + " must be a positive non-null value");
        }
    }

    private void validatePositiveIntegerField(Integer value, String fieldName) {
        if (value == null || value <= 0) {
            throw new IllegalArgumentException(fieldName + " must be a positive non-null value");
        }
    }

    private void validatePositiveBigDecimalField(BigDecimal value, String fieldName) {
        if (value == null || value.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(fieldName + " must be a positive non-null value");
        }
    }

    private void validateMFOField(String value) {
        validateStringField(value, "MFO");
        validateFieldLength(value, 6, "MFO");
    }

    private void validateINNField(Long value) {
        validatePositiveLongField(value, "INN");
        validateFieldLength(value.toString(), 10, "INN");
    }

    private void validateCardNumberField(Long value) {
        validatePositiveLongField(value, "Card Number");
        validateFieldLength(value.toString(), 16, "Card Number");
    }

    private void validateOKPOField(Long value) {
        validatePositiveLongField(value, "OKPO");
        validateFieldLength(value.toString(), 8, "OKPO");
    }

    private void validateFieldLength(String value, int expectedLength, String fieldName) {
        if (value.length() != expectedLength) {
            throw new IllegalArgumentException(fieldName + " must be " + expectedLength + " digits long");
        }
    }
}

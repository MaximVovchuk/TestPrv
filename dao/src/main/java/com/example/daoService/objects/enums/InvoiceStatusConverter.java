package com.example.daoService.objects.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class InvoiceStatusConverter implements AttributeConverter<InvoiceStatus, Character> {

    @Override
    public Character convertToDatabaseColumn(InvoiceStatus role) {
        if (role == null) {
            return null;
        }
        return role.getStatus();
    }

    @Override
    public InvoiceStatus convertToEntityAttribute(Character character) {
        if (character == null) {
            return null;
        }
        return Stream.of(InvoiceStatus.values())
                .filter(c -> character.equals(c.getStatus()))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}

package com.example.daoService.objects.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Entity
@Table(name = "payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "sender_name")
    private String senderName;
    @Column(name = "inn")
    private Long inn;
    @Column(name = "card_number")
    private Long cardNumber;
    @Column(name = "account")
    private Long account;
    @Column(name = "mfo")
    private String mfo;
    @Column(name = "okpo")
    private Long okpo;
    @Column(name = "getter_name")
    private String getterName;
    @Column(name = "period")
    private Integer period;//days
    @Column(name = "amount")
    private BigDecimal amount;
}
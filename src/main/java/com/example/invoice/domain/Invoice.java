package com.example.invoice.domain;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "invoice")
public class Invoice implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long invoiceCode;

    private Long profitMargin;

    private Long quantity;

    private Long amountPerUnit;

    private Long total;

    private Long sub;

    private Long main;

    private Boolean mainAmountPerItem;


}

package com.example.invoice.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class InvoiceCommand implements Serializable {

    private Long invoiceCode;

    private Long profitMargin;

    private Long quantity;

    private Long amountPerUnit;

    private Long total;

    private Long sub;

    private Long main;

    private Boolean mainAmountPerItem;
}

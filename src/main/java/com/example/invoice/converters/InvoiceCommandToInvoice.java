package com.example.invoice.converters;

import com.example.invoice.commands.InvoiceCommand;
import com.example.invoice.domain.Invoice;
import io.micrometer.common.lang.Nullable;
import lombok.RequiredArgsConstructor;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class InvoiceCommandToInvoice implements Converter<InvoiceCommand, Invoice> {

    @Synchronized
    @Nullable
    @Override
    public Invoice convert(InvoiceCommand source) {

        if (source == null) {
            return null;
        }

        final Invoice invoice = new Invoice();
        invoice.setInvoiceCode(source.getInvoiceCode());
        invoice.setMain(source.getMain());
        invoice.setSub(source.getSub());
        invoice.setTotal(source.getTotal());
        invoice.setQuantity(source.getQuantity());
        invoice.setProfitMargin(source.getProfitMargin());
        invoice.setAmountPerUnit(source.getAmountPerUnit());
        invoice.setMainAmountPerItem(source.getMainAmountPerItem());
        return invoice;
    }
}

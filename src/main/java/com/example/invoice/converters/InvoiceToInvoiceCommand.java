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
public class InvoiceToInvoiceCommand implements Converter<Invoice, InvoiceCommand> {


    @Synchronized
    @Nullable
    @Override
    public InvoiceCommand convert(Invoice source) {

        if (source == null) {
            return null;
        }

        final InvoiceCommand invoiceCommand = new InvoiceCommand();
        invoiceCommand.setInvoiceCode(source.getInvoiceCode());
        invoiceCommand.setMain(source.getMain());
        invoiceCommand.setSub(source.getSub());
        invoiceCommand.setTotal(source.getTotal());
        invoiceCommand.setQuantity(source.getQuantity());
        invoiceCommand.setProfitMargin(source.getProfitMargin());
        invoiceCommand.setAmountPerUnit(source.getAmountPerUnit());
        invoiceCommand.setMainAmountPerItem(source.getMainAmountPerItem());
        return invoiceCommand;
    }
}

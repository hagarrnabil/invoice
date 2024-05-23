package com.example.invoice.services;

import com.example.invoice.commands.InvoiceCommand;
import com.example.invoice.domain.Invoice;

import java.util.Set;

public interface InvoiceService {

    Set<InvoiceCommand> getInvoiceCommands();

    Invoice findById(Long l);

    void deleteById(Long idToDelete);

    InvoiceCommand saveInvoiceCommand(InvoiceCommand command);

    Invoice updateInvoice(InvoiceCommand newInvoiceCommand, Long l);

    InvoiceCommand findInvoiceCommandById(Long l);
}

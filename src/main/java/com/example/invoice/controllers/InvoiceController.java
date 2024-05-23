package com.example.invoice.controllers;

import com.example.invoice.commands.InvoiceCommand;
import com.example.invoice.converters.InvoiceToInvoiceCommand;
import com.example.invoice.repositories.InvoiceRepository;
import com.example.invoice.services.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
@RestController
public class InvoiceController {

    private final InvoiceRepository invoiceRepository;

    private final InvoiceService invoiceService;

    private final InvoiceToInvoiceCommand invoiceToInvoiceCommand;

    @GetMapping("/invoices")
    Set<InvoiceCommand> all() {
        return invoiceService.getInvoiceCommands();
    }

    @GetMapping("/invoices/{invoiceCode}")
    public Optional<InvoiceCommand> findByIds(@PathVariable Long invoiceCode) {

        return Optional.ofNullable(invoiceService.findInvoiceCommandById(invoiceCode));
    }

    @PostMapping("/invoices")
    InvoiceCommand newInvoiceCommand(@RequestBody InvoiceCommand newInvoiceCommand) {

        InvoiceCommand savedCommand = invoiceService.saveInvoiceCommand(newInvoiceCommand);
        return savedCommand;

    }

    @DeleteMapping("/invoices/{invoiceCode}")
    void deleteInvoiceCommand(@PathVariable Long invoiceCode) {
        invoiceService.deleteById(invoiceCode);
    }

    @PutMapping
    @RequestMapping("/invoices/{invoiceCode}")
    @Transactional
    InvoiceCommand updateInvoiceCommand(@RequestBody InvoiceCommand newInvoiceCommand, @PathVariable Long invoiceCode) {

        InvoiceCommand command = invoiceToInvoiceCommand.convert(invoiceService.updateInvoice(newInvoiceCommand, invoiceCode));
        return command;
    }

}

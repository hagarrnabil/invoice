package com.example.invoice.services;

import com.example.invoice.commands.InvoiceCommand;
import com.example.invoice.converters.InvoiceCommandToInvoice;
import com.example.invoice.converters.InvoiceToInvoiceCommand;
import com.example.invoice.domain.Invoice;
import com.example.invoice.repositories.InvoiceRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RequiredArgsConstructor
@Slf4j
@Service
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final InvoiceToInvoiceCommand invoiceToInvoiceCommand;
    private final InvoiceCommandToInvoice invoiceCommandToInvoice;

    @Override
    @Transactional
    public Set<InvoiceCommand> getInvoiceCommands() {
        return StreamSupport.stream(invoiceRepository.findAll()
                        .spliterator(), false)
                .map(invoiceToInvoiceCommand::convert)
                .collect(Collectors.toSet());

    }

    @Override
    public Invoice findById(Long l) {
        Optional<Invoice> invoiceOptional = invoiceRepository.findById(l);

        if (!invoiceOptional.isPresent()) {
            throw new RuntimeException("Invoice Not Found!");
        }

        return invoiceOptional.get();
    }

    @Override
    public void deleteById(Long idToDelete) {
        invoiceRepository.deleteById(idToDelete);
    }

    @Override
    @Transactional
    public InvoiceCommand saveInvoiceCommand(InvoiceCommand command) {
        Invoice detachedInvoice = invoiceCommandToInvoice.convert(command);
        Invoice savedInvoice = invoiceRepository.save(detachedInvoice);
        log.debug("Saved Invoice Id:" + savedInvoice.getInvoiceCode());
        return invoiceToInvoiceCommand.convert(savedInvoice);
    }

    @Override
    public Invoice updateInvoice(InvoiceCommand newInvoiceCommand, Long l) {
        return invoiceRepository.findById(l).map(oldInvoice -> {
            if (newInvoiceCommand.getInvoiceCode() != oldInvoice.getInvoiceCode())
                oldInvoice.setInvoiceCode(newInvoiceCommand.getInvoiceCode());
            return invoiceRepository.save(oldInvoice);
        }).orElseThrow(() -> new RuntimeException("Invoice not found"));

    }

    @Override
    @Transactional
    public InvoiceCommand findInvoiceCommandById(Long l) {
        return invoiceToInvoiceCommand.convert(findById(l));
    }
}

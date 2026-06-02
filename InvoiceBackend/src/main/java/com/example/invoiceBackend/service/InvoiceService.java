package com.example.invoiceBackend.service;

import java.io.IOException;
import java.nio.file.*;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.invoiceBackend.entity.Invoice;
import com.example.invoiceBackend.repository.InvoiceRepository;

@Service
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;

    private final String UPLOAD_DIR =
            "uploads/invoices/";

    public InvoiceService(
            InvoiceRepository invoiceRepository) {

        this.invoiceRepository = invoiceRepository;
    }

    public String uploadInvoice(
            String invoiceNo,
            MultipartFile file)
            throws IOException {

        if(invoiceRepository
                .findByInvoiceNo(invoiceNo)
                .isPresent()) {

            return "Invoice Already Exists";
        }

        Files.createDirectories(
                Paths.get(UPLOAD_DIR));

        String fileName =
                invoiceNo + "_" +
                file.getOriginalFilename();

        Path path =
                Paths.get(UPLOAD_DIR + fileName);

        Files.write(
                path,
                file.getBytes());

        Invoice invoice =
                new Invoice(
                        invoiceNo,
                        fileName);

        invoiceRepository.save(invoice);

        return "Invoice Uploaded Successfully";
    }
}
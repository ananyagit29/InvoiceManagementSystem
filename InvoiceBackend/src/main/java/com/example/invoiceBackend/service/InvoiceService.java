package com.example.invoiceBackend.service;

import java.io.File;
import java.text.SimpleDateFormat;

import org.springframework.stereotype.Service;

import com.example.invoiceBackend.dto.InvoiceSearchResponse;
import com.example.invoiceBackend.primary.repository.InvoiceRepository;

@Service
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;

    private static final String INVOICE_STORAGE =
            "D:/InvoiceStorage/";

    public InvoiceService(
            InvoiceRepository invoiceRepository) {

        this.invoiceRepository =
                invoiceRepository;
    }

    public InvoiceSearchResponse searchInvoice(
            String invoiceNo) {

        InvoiceSearchResponse response =
                new InvoiceSearchResponse();

        File storageFolder =
                new File(INVOICE_STORAGE);

        if (!storageFolder.exists()) {

            response.setFound(false);

            return response;
        }

        File[] files =
                storageFolder.listFiles();

        if (files == null) {

            response.setFound(false);

            return response;
        }

        for (File file : files) {

            String fileName =
                    file.getName();

            if (fileName
                    .toLowerCase()
                    .contains(
                            invoiceNo
                                    .toLowerCase())) {

                response.setFound(true);

                response.setInvoiceNo(
                        invoiceNo);

                response.setInvoiceFile(
                        fileName);

                response.setCreatedBy(
                        "Admin");

                response.setCreatedOn(
                        new SimpleDateFormat(
                                "dd-MM-yyyy HH:mm:ss")
                                .format(
                                        new java.util.Date(file.lastModified())));

                return response;
            }
        }

        response.setFound(false);

        return response;
    }
}
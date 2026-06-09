package com.example.invoiceBackend.service;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import com.example.invoiceBackend.dto.InvoiceSearchResponse;
import com.example.invoiceBackend.primary.entity.Invoice;
import com.example.invoiceBackend.primary.repository.InvoiceRepository;
import com.example.invoiceBackend.secondary.entity.CorrespondingFile;
import com.example.invoiceBackend.secondary.repository.CorrespondingFileRepository;

@Service
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;

    private final CorrespondingFileRepository correspondingFileRepository;

    private static final String INVOICE_STORAGE =
            "D:/InvoiceStorage/";

    public InvoiceService(
            InvoiceRepository invoiceRepository,
            CorrespondingFileRepository correspondingFileRepository) {

        this.invoiceRepository =
                invoiceRepository;

        this.correspondingFileRepository =
                correspondingFileRepository;
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

                List<CorrespondingFile> otherFiles =
                        correspondingFileRepository
                                .findByInvoiceNo(
                                        invoiceNo);

                if (!otherFiles.isEmpty()) {

                    response.setOtherFile(
                            otherFiles
                                    .get(0)
                                    .getFileName());
                }

                Invoice invoice =
                        invoiceRepository
                                .findByInvoiceNo(
                                        invoiceNo)
                                .orElse(null);

                response.setCreatedBy(
                        invoice != null &&
                                invoice.getUsername() != null &&
                                !invoice.getUsername().isBlank()
                                ? invoice.getUsername()
                                : "Admin");

                response.setCreatedOn(
                        invoice != null &&
                                invoice.getCreatedOn() != null &&
                                !invoice.getCreatedOn().isBlank()
                                ? invoice.getCreatedOn()
                                : new SimpleDateFormat(
                                        "dd-MM-yyyy HH:mm:ss")
                                        .format(
                                                new java.util.Date(file.lastModified())));

                return response;
            }
        }

        response.setFound(false);

        return response;
    }

    public Resource getInvoiceFile(
            String invoiceNo)
            throws Exception {

        File invoiceFile =
                findInvoiceFile(
                        invoiceNo);

        if (invoiceFile == null) {

            throw new Exception(
                    "Invoice file not found");
        }

        Path filePath =
                Paths.get(
                        invoiceFile
                                .getAbsolutePath())
                        .normalize();

        return new UrlResource(
                filePath
                        .toUri());
    }

    public String getInvoiceFileName(
            String invoiceNo)
            throws Exception {

        File invoiceFile =
                findInvoiceFile(
                        invoiceNo);

        if (invoiceFile == null) {

            throw new Exception(
                    "Invoice file not found");
        }

        return invoiceFile
                .getName();
    }

    private File findInvoiceFile(
            String invoiceNo) {

        File storageFolder =
                new File(
                        INVOICE_STORAGE);

        File[] files =
                storageFolder
                        .listFiles();

        if (files == null) {

            return null;
        }

        for (File file : files) {

            if (file.isFile() &&
                    file.getName()
                            .toLowerCase()
                            .contains(
                                    invoiceNo
                                            .toLowerCase())) {

                return file;
            }
        }

        return null;
    }
}

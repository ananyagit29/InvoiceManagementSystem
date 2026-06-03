package com.example.invoiceBackend.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.invoiceBackend.entity.CorrespondingFile;
import com.example.invoiceBackend.entity.Invoice;
import com.example.invoiceBackend.repository.CorrespondingFileRepository;
import com.example.invoiceBackend.repository.InvoiceRepository;

@Service
public class CorrespondingFileService {

    private final InvoiceRepository invoiceRepository;

    private final CorrespondingFileRepository correspondingFileRepository;

    private final String SUPPORTING_DIR =
            "E:/SupportingDocuments/";

    public CorrespondingFileService(
            InvoiceRepository invoiceRepository,
            CorrespondingFileRepository correspondingFileRepository) {

        this.invoiceRepository =
                invoiceRepository;

        this.correspondingFileRepository =
                correspondingFileRepository;
    }

    public String uploadFile(
            String invoiceNo,
            String username,
            String fileType,
            MultipartFile file)
            throws IOException {

        Invoice invoice =
                invoiceRepository
                        .findByInvoiceNo(invoiceNo)
                        .orElse(null);

        if (invoice == null) {

            return "Invoice Not Found";
        }

        if (!invoice.getUsername()
                .equals(username)) {

            return "Access Denied";
        }

        Path invoiceFolder =
                Paths.get(
                        SUPPORTING_DIR +
                                invoiceNo);

        Files.createDirectories(
                invoiceFolder);

        String fileName =
                file.getOriginalFilename();

        Path filePath =
                invoiceFolder.resolve(
                        fileName);

        Files.write(
                filePath,
                file.getBytes());

        CorrespondingFile doc =
                new CorrespondingFile(
                        invoiceNo,
                        fileType,
                        fileName,
                        username);

        correspondingFileRepository
                .save(doc);

        return "Supporting File Uploaded";
    }
}
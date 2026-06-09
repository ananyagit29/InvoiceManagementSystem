package com.example.invoiceBackend.service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.invoiceBackend.secondary.entity.CorrespondingFile;
import com.example.invoiceBackend.secondary.repository.CorrespondingFileRepository;

@Service
public class CorrespondingFileService {

    private final CorrespondingFileRepository repository;

    private static final String
            SUPPORTING_STORAGE =
            "E:/SupportingDocuments/";

    private static final String
            INVOICE_STORAGE =
            "D:/InvoiceStorage/";

    public CorrespondingFileService(
            CorrespondingFileRepository repository) {

        this.repository =
                repository;
    }

    public String uploadFile(
            String invoiceNo,
            String fileType,
            String username,
            MultipartFile file)
            throws Exception {

        boolean invoiceExists =
                false;

        File invoiceFolder =
                new File(
                        INVOICE_STORAGE);

        File[] files =
                invoiceFolder.listFiles();

        if (files != null) {

            for (File invoice :
                    files) {

                if (invoice.getName()
                        .contains(
                                invoiceNo)) {

                    invoiceExists =
                            true;

                    break;
                }
            }
        }

        if (!invoiceExists) {

            return "Invoice Not Found";
        }

        Path targetFolder =
                Paths.get(
                        SUPPORTING_STORAGE
                                + invoiceNo);

        Files.createDirectories(
                targetFolder);

        String fileName =
                file.getOriginalFilename();

        Path destination =
                targetFolder.resolve(
                        fileName);

        Files.write(
                destination,
                file.getBytes());

        CorrespondingFile doc =
                new CorrespondingFile();

        doc.setInvoiceNo(
                invoiceNo);

        doc.setFileType(
                fileType);

        doc.setFileName(
                fileName);

        doc.setUsername(
                username);

        doc.setCreatedOn(
                LocalDateTime
                        .now()
                        .toString());

        repository.save(
                doc);

        return "File Uploaded Successfully";
    }

    public Resource getSupportingFile(
            String invoiceNo)
            throws Exception {

        Path filePath =
                getSupportingFilePath(
                        invoiceNo);

        return new UrlResource(
                filePath
                        .toUri());
    }

    public String getSupportingFileName(
            String invoiceNo)
            throws Exception {

        return getSupportingFilePath(
                invoiceNo)
                .getFileName()
                .toString();
    }

    private Path getSupportingFilePath(
            String invoiceNo)
            throws Exception {

        List<CorrespondingFile> files =
                repository.findByInvoiceNo(
                        invoiceNo);

        if (files.isEmpty()) {

            throw new Exception(
                    "Supporting file not found");
        }

        String fileName =
                files.get(0)
                        .getFileName();

        Path invoiceFolder =
                Paths.get(
                        SUPPORTING_STORAGE,
                        invoiceNo)
                        .normalize();

        Path filePath =
                invoiceFolder
                        .resolve(
                                fileName)
                        .normalize();

        if (!filePath.startsWith(
                invoiceFolder) ||
                !Files.exists(
                        filePath)) {

            throw new Exception(
                    "Supporting file not found");
        }

        return filePath;
    }
}

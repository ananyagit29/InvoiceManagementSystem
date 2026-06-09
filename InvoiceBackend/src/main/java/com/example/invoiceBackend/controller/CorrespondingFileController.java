package com.example.invoiceBackend.controller;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.invoiceBackend.service.CorrespondingFileService;

@RestController
@RequestMapping(
        "/api/corresponding-file")
@CrossOrigin(
        origins =
                "http://localhost:5173")
public class CorrespondingFileController {

    private final
    CorrespondingFileService
            service;

    public CorrespondingFileController(
            CorrespondingFileService service) {

        this.service =
                service;
    }

    @PostMapping("/upload")
    public ResponseEntity<String>
    uploadFile(

            @RequestParam
            String invoiceNo,

            @RequestParam
            String fileType,

            @RequestParam
            String username,

            @RequestParam
            MultipartFile file) {

        try {

            String result =
                    service.uploadFile(
                            invoiceNo,
                            fileType,
                            username,
                            file);

            return ResponseEntity
                    .ok(result);

        } catch (Exception e) {

            return ResponseEntity
                    .badRequest()
                    .body(
                            e.getMessage());
        }
    }

    @GetMapping("/{invoiceNo}/view")
    public ResponseEntity<Resource>
    viewFile(
            @PathVariable
            String invoiceNo)
            throws Exception {

        Resource file =
                service.getSupportingFile(
                        invoiceNo);

        return ResponseEntity.ok()
                .contentType(
                        MediaType.APPLICATION_OCTET_STREAM)
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "inline; filename=\"" +
                                service.getSupportingFileName(
                                        invoiceNo) +
                                "\"")
                .body(
                        file);
    }

    @GetMapping("/{invoiceNo}/download")
    public ResponseEntity<Resource>
    downloadFile(
            @PathVariable
            String invoiceNo)
            throws Exception {

        Resource file =
                service.getSupportingFile(
                        invoiceNo);

        return ResponseEntity.ok()
                .contentType(
                        MediaType.APPLICATION_OCTET_STREAM)
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" +
                                service.getSupportingFileName(
                                        invoiceNo) +
                                "\"")
                .body(
                        file);
    }
}

package com.example.invoiceBackend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.invoiceBackend.service.CorrespondingFileService;

@RestController
@RequestMapping("/api/corresponding-file")
@CrossOrigin(origins = "http://localhost:5173")
public class CorrespondingFileController {

    private final CorrespondingFileService service;

    public CorrespondingFileController(
            CorrespondingFileService service) {

        this.service = service;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> upload(

            @RequestParam String invoiceNo,

            @RequestParam String username,

            @RequestParam String fileType,

            @RequestParam MultipartFile file) {

        try {

            String result =
                    service.uploadFile(
                            invoiceNo,
                            username,
                            fileType,
                            file);

            return ResponseEntity.ok(result);

        } catch (Exception e) {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }
}
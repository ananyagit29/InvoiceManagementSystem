package com.example.invoiceBackend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.invoiceBackend.service.InvoiceService;

@RestController
@RequestMapping("/api/invoice")
@CrossOrigin(origins = "http://localhost:5173")
public class InvoiceController {

    private final InvoiceService invoiceService;

    public InvoiceController(
            InvoiceService invoiceService) {

        this.invoiceService = invoiceService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadInvoice(
            @RequestParam String invoiceNo,
            @RequestParam MultipartFile file) {

        try {

            String result =
                    invoiceService.uploadInvoice(
                            invoiceNo,
                            file);

            return ResponseEntity.ok(result);

        } catch(Exception e) {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }
}
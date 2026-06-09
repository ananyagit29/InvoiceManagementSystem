package com.example.invoiceBackend.controller;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.invoiceBackend.dto.InvoiceSearchResponse;
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

    // ==========================
    // SEARCH INVOICE API
    // ==========================

    @GetMapping("/search/{invoiceNo}")
    public ResponseEntity<InvoiceSearchResponse>
    searchInvoice(
            @PathVariable String invoiceNo) {

        InvoiceSearchResponse response =
                invoiceService.searchInvoice(
                        invoiceNo);

        return ResponseEntity.ok(
                response);
    }

    @GetMapping("/file/{invoiceNo}/view")
    public ResponseEntity<Resource> viewInvoiceFile(
            @PathVariable String invoiceNo)
            throws Exception {

        Resource file =
                invoiceService.getInvoiceFile(
                        invoiceNo);

        return ResponseEntity.ok()
                .contentType(
                        MediaType.APPLICATION_OCTET_STREAM)
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "inline; filename=\"" +
                                invoiceService.getInvoiceFileName(
                                        invoiceNo) +
                                "\"")
                .body(
                        file);
    }

    @GetMapping("/file/{invoiceNo}/download")
    public ResponseEntity<Resource> downloadInvoiceFile(
            @PathVariable String invoiceNo)
            throws Exception {

        Resource file =
                invoiceService.getInvoiceFile(
                        invoiceNo);

        return ResponseEntity.ok()
                .contentType(
                        MediaType.APPLICATION_OCTET_STREAM)
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" +
                                invoiceService.getInvoiceFileName(
                                        invoiceNo) +
                                "\"")
                .body(
                        file);
    }
}

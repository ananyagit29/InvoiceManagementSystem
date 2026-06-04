package com.example.invoiceBackend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;      // NEW
import org.springframework.web.bind.annotation.PathVariable; // NEW
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
}
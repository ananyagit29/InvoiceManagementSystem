package com.example.invoiceBackend.primary.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.invoiceBackend.primary.entity.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    Optional<Invoice> findByInvoiceNo(String invoiceNo);
}

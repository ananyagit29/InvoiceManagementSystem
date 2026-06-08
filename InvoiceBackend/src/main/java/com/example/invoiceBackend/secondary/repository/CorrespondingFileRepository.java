package com.example.invoiceBackend.secondary.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.invoiceBackend.secondary.entity.CorrespondingFile;

public interface CorrespondingFileRepository extends JpaRepository<CorrespondingFile, Long> {
    List<CorrespondingFile> findByInvoiceNo(String invoiceNo);
}

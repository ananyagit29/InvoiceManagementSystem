package com.example.invoiceBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.invoiceBackend.entity.CorrespondingFile;

public interface CorrespondingFileRepository
        extends JpaRepository<
        CorrespondingFile,
        Long> {
}
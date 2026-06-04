package com.example.invoiceBackend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "corresponding_files")
public class CorrespondingFile {

    @Id
    @GeneratedValue(strategy =
            GenerationType.IDENTITY)
    private Long id;

    private String invoiceNo;

    private String fileType;

    private String fileName;

    private String username;

    private String createdOn;

    public CorrespondingFile() {
    }

    public Long getId() {
        return id;
    }

    public void setId(
            Long id) {
        this.id = id;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(
            String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(
            String fileType) {
        this.fileType = fileType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(
            String fileName) {
        this.fileName = fileName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(
            String username) {
        this.username = username;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(
            String createdOn) {
        this.createdOn = createdOn;
    }
}
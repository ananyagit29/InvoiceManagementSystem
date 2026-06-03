package com.example.invoiceBackend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "corresponding_files")
public class CorrespondingFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String invoiceNo;

    private String fileType;

    private String fileName;

    private String username;

    public CorrespondingFile() {
    }

    public CorrespondingFile(
            String invoiceNo,
            String fileType,
            String fileName,
            String username) {

        this.invoiceNo = invoiceNo;
        this.fileType = fileType;
        this.fileName = fileName;
        this.username = username;
    }

    public Long getId() {
        return id;
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
}
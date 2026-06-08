package com.example.invoiceBackend.dto;

public class CorrespondingFileDto {
    private Long id;
    private String invoiceNo;
    private String fileType;
    private String fileName;
    private String username;
    private String createdOn;

    public CorrespondingFileDto() {
    }

    public CorrespondingFileDto(Long id, String invoiceNo, String fileType, String fileName, String username, String createdOn) {
        this.id = id;
        this.invoiceNo = invoiceNo;
        this.fileType = fileType;
        this.fileName = fileName;
        this.username = username;
        this.createdOn = createdOn;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }
}

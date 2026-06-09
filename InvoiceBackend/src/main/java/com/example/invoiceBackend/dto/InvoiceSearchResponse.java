package com.example.invoiceBackend.dto;

public class InvoiceSearchResponse {

    private String invoiceNo;

    private String invoiceFile;

    private String otherFile;

    private String createdBy;

    private String createdOn;

    private boolean found;

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(
            String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public String getInvoiceFile() {
        return invoiceFile;
    }

    public void setInvoiceFile(
            String invoiceFile) {
        this.invoiceFile = invoiceFile;
    }

    public String getOtherFile() {
        return otherFile;
    }

    public void setOtherFile(
            String otherFile) {
        this.otherFile = otherFile;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(
            String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(
            String createdOn) {
        this.createdOn = createdOn;
    }

    public boolean isFound() {
        return found;
    }

    public void setFound(
            boolean found) {
        this.found = found;
    }
}

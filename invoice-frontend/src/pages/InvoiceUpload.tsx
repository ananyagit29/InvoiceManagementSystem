import { useState } from "react";
import api from "../services/api";

function InvoiceUpload() {

    const [invoiceNo, setInvoiceNo] =
        useState("");

    const [file, setFile] =
        useState<File | null>(null);

    const uploadInvoice = async () => {

        if (!file) {
            alert("Select Invoice PDF");
            return;
        }

        const formData =
            new FormData();

        formData.append(
            "invoiceNo",
            invoiceNo
        );

        formData.append(
            "file",
            file
        );

        try {

            const response =
                await api.post(
                    "/invoice/upload",
                    formData,
                    {
                        headers: {
                            "Content-Type":
                                "multipart/form-data"
                        }
                    }
                );

            alert(response.data);

        } catch (error) {

            alert("Upload Failed");
        }
    };

    return (
        <div>

            <h2>Upload Invoice</h2>

            <input
                type="text"
                placeholder="Invoice Number"
                value={invoiceNo}
                onChange={(e) =>
                    setInvoiceNo(
                        e.target.value
                    )
                }
            />

            <br />
            <br />

            <label htmlFor="invoiceFile">Invoice PDF</label>
            <input
                id="invoiceFile"
                type="file"
                accept=".pdf"
                onChange={(e) => {
                    if (
                        e.target.files &&
                        e.target.files[0]
                    ) {
                        setFile(e.target.files[0]);
                    }
                }}
            />

            <br />
            <br />

            <button
                onClick={uploadInvoice}
            >
                Upload
            </button>

        </div>
    );
}

export default InvoiceUpload;
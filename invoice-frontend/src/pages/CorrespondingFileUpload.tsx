import { useState } from "react";
import axios from "axios";

function CorrespondingFileUpload() {

    const [invoiceNo, setInvoiceNo] =
        useState("");

    const [fileType, setFileType] =
        useState("");

    const [file, setFile] =
        useState<File | null>(null);

    const upload = async () => {

        if (!file) {

            alert("Choose File");

            return;
        }

        const username =
            localStorage.getItem(
                "username"
            );

        const formData =
            new FormData();

        formData.append(
            "invoiceNo",
            invoiceNo
        );

        formData.append(
            "fileType",
            fileType
        );

        formData.append(
            "username",
            username || ""
        );

        formData.append(
            "file",
            file
        );

        try {

            const response =
                await axios.post(
                    "http://localhost:8080/api/corresponding-file/upload",
                    formData
                );

            alert(response.data);

        } catch (error: any) {

            alert(
                error.response.data
            );
        }
    };

    return (

        <div>

            <h2>
                Upload Corresponding File
            </h2>

            <label htmlFor="invoiceNo">
                Invoice Number
            </label>
            
            <input
            id="invoiceNo"
            type="text"
            value={invoiceNo}
            onChange={(e) =>setInvoiceNo(e.target.value)} />
            
            <br /><br />

            <label htmlFor="fileType">
                File Type
            </label>
            
            <select
            id="fileType"
            value={fileType}
            onChange={(e) =>setFileType(e.target.value)} >

                <option value="">
                    Select Type
                </option>

                <option value="PO">
                    PO
                </option>

                <option value="Quotation">
                    Quotation
                </option>

                <option value="Receipt">
                    Receipt
                </option>

                <option value="DeliveryChallan">
                    Delivery Challan
                </option>

            </select>

            <br /><br />

            <label htmlFor="supportingFile">
                Supporting File
            </label>
            
            <input
            id="supportingFile"
            type="file"
            onChange={(e) =>setFile(e.target.files?.[0] || null)} />

            <br /><br />

            <button
                onClick={upload}>
                Upload
            </button>

        </div>
    );
}

export default CorrespondingFileUpload;
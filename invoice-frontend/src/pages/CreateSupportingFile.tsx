import { useState } from "react";
import axios from "axios";

function CreateSupportingFile() {

    const [invoiceNo, setInvoiceNo] =
        useState("");

    const [invoiceFound, setInvoiceFound] =
        useState(false);

    const [fileType, setFileType] =
        useState("");

    const [file, setFile] =
        useState<File | null>(null);

    const validateInvoice =
        async () => {

            if (!invoiceNo) {

                alert(
                    "Enter Invoice Number"
                );

                return;
            }

            try {

                const response =
                    await axios.get(
                        `http://localhost:8080/api/invoice/search/${invoiceNo}`
                    );

                if (
                    response.data.found
                ) {

                    setInvoiceFound(
                        true
                    );

                } else {

                    setInvoiceFound(
                        false
                    );

                    alert(
                        "Invoice Not Found"
                    );
                }

            } catch {

                alert(
                    "Validation Failed"
                );
            }
        };

    const uploadFile =
        async () => {

            if (!file) {

                alert(
                    "Select File"
                );

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

                alert(
                    response.data
                );

            } catch (error: any) {

                alert(
                    error.response?.data ||
                    "Upload Failed"
                );
            }
        };

    return (

        <div>

            <h2>
                Upload Supporting File
            </h2>

            <input
                type="text"
                placeholder="Invoice Number"
                value={invoiceNo}
                onChange={(e) => {
                    setInvoiceNo(e.target.value);
                    setInvoiceFound(false);
                }}
            />

            <br />
            <br />

            <button
                onClick={
                    validateInvoice
                }
            >
                Validate Invoice
            </button>

            <br />
            <br />

            {
                invoiceFound && (

                    <div>

                        <h3>
                            Invoice Found
                        </h3>

                        <label htmlFor="fileTypeSelect">
                            File Type
                        </label>

                        <select
                            id="fileTypeSelect"
                            title="Select file type"
                            value={
                                fileType
                            }
                            onChange={(e) =>
                                setFileType(
                                    e.target.value
                                )
                            }
                        >

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

                        <br />
                        <br />

                        <label htmlFor="supportingFile">
                            Supporting File
                        </label>

                        <input
                            id="supportingFile"
                            type="file"
                            onChange={(e) =>
                                setFile(
                                    e.target
                                        .files?.[0]
                                        || null
                                )
                            }
                        />

                        <br />
                        <br />

                        <button
                            onClick={
                                uploadFile
                            }
                        >
                            Upload
                        </button>

                    </div>
                )
            }

        </div>
    );
}

export default CreateSupportingFile;
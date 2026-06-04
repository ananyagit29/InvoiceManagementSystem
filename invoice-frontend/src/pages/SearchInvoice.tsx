import { useState } from "react";
import axios from "axios";

interface InvoiceData {

    invoiceNo: string;

    invoiceFile: string;

    createdBy: string;

    createdOn: string;

    found: boolean;
}

function SearchInvoice() {

    const [invoiceNo, setInvoiceNo] =
        useState("");

    const [searchType, setSearchType] =
        useState("Invoice Number");

    const [invoiceData, setInvoiceData] =
        useState<InvoiceData | null>(null);

    const searchInvoice =
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

                setInvoiceData(
                    response.data
                );

            } catch {

                alert(
                    "Search Failed"
                );
            }
        };

    return (

        <div>

            <h2>
                Search Invoice
            </h2>

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

            <label htmlFor="searchTypeSelect">
                Search Type
            </label>
            <select id="searchTypeSelect" value={searchType}
                onChange={(e) =>
                    setSearchType(
                        e.target.value
                    )
                }
            >
                <option>
                    Invoice Number
                </option>
            </select>

            <br />
            <br />

            <button
                onClick={
                    searchInvoice
                }
            >
                Search
            </button>

            <br />
            <br />

            {
                invoiceData &&
                invoiceData.found && (

                    <table
                        border={1}
                    >
                        <thead>

                        <tr>

                            <th>
                                Invoice No
                            </th>

                            <th>
                                Invoice File
                            </th>

                            <th>
                                Other File
                            </th>

                            <th>
                                Created By
                            </th>

                            <th>
                                Created On
                            </th>

                        </tr>

                        </thead>

                        <tbody>

                        <tr>

                            <td>
                                {
                                    invoiceData.invoiceNo
                                }
                            </td>

                            <td>
                                {
                                    invoiceData.invoiceFile
                                }
                            </td>

                            <td>
                                Available
                            </td>

                            <td>
                                {
                                    invoiceData.createdBy
                                }
                            </td>

                            <td>
                                {
                                    invoiceData.createdOn
                                }
                            </td>

                        </tr>

                        </tbody>

                    </table>
                )
            }

            {
                invoiceData &&
                !invoiceData.found && (

                    <h3>
                        Invoice Not Found
                    </h3>
                )
            }

        </div>
    );
}

export default SearchInvoice;
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import api from "../services/api";

interface InvoiceData {

    invoiceNo: string;

    otherFile?: string;

    invoiceFile: string;

    createdBy: string;

    createdOn: string;

    found: boolean;
}

function SearchInvoice() {

    const navigate =
        useNavigate();

    const [invoiceNo, setInvoiceNo] =
        useState("");

    const [searchType, setSearchType] =
        useState("Invoice Number");

    const [invoiceData, setInvoiceData] =
        useState<InvoiceData | null>(null);

    const [hasSearched, setHasSearched] =
        useState(false);

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
                    await api.get(
                        `/invoice/search/${invoiceNo}`
                    );

                setInvoiceData(
                    response.data
                );

                setHasSearched(
                    true
                );

            } catch {

                alert(
                    "Search Failed"
                );
            }
        };

    const clearSearch =
        () => {

            setInvoiceNo("");
            setSearchType("Invoice Number");
            setInvoiceData(null);
            setHasSearched(false);
        };

    const openFile =
        (url: string) => {

            window.open(
                url,
                "_blank",
                "noopener,noreferrer"
            );
        };

    const baseUrl =
        "http://localhost:8080/api";

    return (

        <div className="ims-page">

            <header className="ims-topbar">
                <div className="ims-user-block">
                    <div className="ims-avatar">
                        AP
                    </div>
                    <div>
                        <div className="ims-user-name">
                            {
                                localStorage.getItem("username") ||
                                "Ananya Parbat"
                            }
                        </div>
                        <div className="ims-user-meta">
                            ananya.parbat - Mumbai - IT
                        </div>
                    </div>
                </div>

                <div className="ims-top-actions">
                    <button
                        className="ims-icon-button"
                        type="button"
                        title="Dashboard"
                        onClick={() =>
                            navigate(
                                "/dashboard"
                            )
                        }
                    >
                        Home
                    </button>
                    <button
                        className="ims-icon-button"
                        type="button"
                        title="Logout"
                        onClick={() => {
                            localStorage.clear();
                            navigate("/");
                        }}
                    >
                        Exit
                    </button>
                </div>
            </header>

            <section className="ims-modulebar">
                <div className="ims-title-block">
                    <span>
                        Dashboard &gt; Invoice Details &gt; Search
                    </span>
                    <h1>
                        Search Invoice
                    </h1>
                </div>

                <div className="ims-context">
                    <div>
                        <span>
                            COMPANY
                        </span>
                        <strong>
                            1
                        </strong>
                    </div>
                    <div>
                        <span>
                            DIVISION
                        </span>
                        <strong>
                            HO
                        </strong>
                    </div>
                    <div>
                        <span>
                            LOCATION
                        </span>
                        <strong>
                            101
                        </strong>
                    </div>
                    <div>
                        <span>
                            SUB APP
                        </span>
                        <strong>
                            COA
                        </strong>
                    </div>
                </div>

                <button
                    className="ims-secondary-top"
                    type="button"
                    onClick={() =>
                        navigate(
                            "/create"
                        )
                    }
                >
                    + Create Invoice
                </button>
            </section>

            <main className="ims-content">
                <section className="ims-filter-panel">
                    <div className="ims-panel-heading">
                        <h2>
                            Search Filters
                        </h2>
                        <button
                            className="ims-clear-button"
                            type="button"
                            onClick={clearSearch}
                        >
                            x Clear
                        </button>
                    </div>

                    <div className="ims-filter-grid ims-filter-grid-single">
                        <label className="ims-field">
                            <span>
                                Invoice Number
                            </span>
                            <div className="ims-combo-field">
                                <input
                                    type="text"
                                    placeholder="Type or select..."
                                    value={invoiceNo}
                                    onChange={(e) =>
                                        setInvoiceNo(
                                            e.target.value
                                        )
                                    }
                                />
                                <select
                                    value={searchType}
                                    title="Search type"
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
                            </div>
                        </label>
                    </div>

                    <div className="ims-filter-actions">
                        <button
                            className="ims-primary-button"
                            type="button"
                            onClick={
                                searchInvoice
                            }
                        >
                            Search
                        </button>
                    </div>
                </section>

                {
                    !hasSearched && (
                        <section className="ims-empty-state">
                            <div className="ims-empty-icon">
                                Clipboard
                            </div>
                            <p>
                                Select filters and click Search to view invoice records
                            </p>
                        </section>
                    )
                }

                {
                    invoiceData &&
                    invoiceData.found && (

                        <section className="ims-table-panel">
                            <table className="ims-results-table">
                                <thead>

                                <tr>

                                    <th>
                                        Invoice No.
                                    </th>

                                    <th>
                                        Other File
                                    </th>

                                    <th>
                                        Invoice File
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
                                        <div className="ims-file-cell">
                                            <span>
                                                {
                                                    invoiceData.otherFile ||
                                                    "No file"
                                                }
                                            </span>
                                            {
                                                invoiceData.otherFile && (
                                                    <div className="ims-file-actions">
                                                        <button
                                                            type="button"
                                                            onClick={() =>
                                                                openFile(
                                                                    `${baseUrl}/corresponding-file/${invoiceData.invoiceNo}/view`
                                                                )
                                                            }
                                                        >
                                                            View
                                                        </button>
                                                        <a
                                                            href={`${baseUrl}/corresponding-file/${invoiceData.invoiceNo}/download`}
                                                        >
                                                            Download
                                                        </a>
                                                    </div>
                                                )
                                            }
                                        </div>
                                    </td>

                                    <td>
                                        <div className="ims-file-cell">
                                            <span>
                                                {
                                                    invoiceData.invoiceFile
                                                }
                                            </span>
                                            <div className="ims-file-actions">
                                                <button
                                                    type="button"
                                                    onClick={() =>
                                                        openFile(
                                                            `${baseUrl}/invoice/file/${invoiceData.invoiceNo}/view`
                                                        )
                                                    }
                                                >
                                                    View
                                                </button>
                                                <a
                                                    href={`${baseUrl}/invoice/file/${invoiceData.invoiceNo}/download`}
                                                >
                                                    Download
                                                </a>
                                            </div>
                                        </div>
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
                        </section>
                    )
                }

                {
                    invoiceData &&
                    !invoiceData.found && (

                        <section className="ims-empty-state">
                            <div className="ims-empty-icon">
                                Search
                            </div>
                            <p>
                                Invoice Not Found
                            </p>
                        </section>
                    )
                }
            </main>

            <footer className="ims-footer">
                ©2026 Copyright <strong>Ipca Laboratories Limited</strong> All Rights Reserved. DMS version 2.0
            </footer>

        </div>
    );
}

export default SearchInvoice;

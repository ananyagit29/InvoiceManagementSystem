import { useNavigate } from "react-router-dom";

import InvoiceUpload from "./InvoiceUpload";
import CorrespondingFileUpload from "./CorrespondingFileUpload";

function Dashboard() {

    const navigate = useNavigate();

    const logout = () => {

        localStorage.clear();

        navigate("/");
    };

    return (
        <div>

            <h1>
                Invoice Management Dashboard
            </h1>

            <InvoiceUpload />

            <hr />

            <CorrespondingFileUpload />

            <hr />

            <button onClick={logout}>
                Logout
            </button>

        </div>
    );
}

export default Dashboard;
import {
    BrowserRouter,
    Routes,
    Route
} from "react-router-dom";

import Login
from "./pages/Login";

import Dashboard
from "./pages/Dashboard";

import SearchInvoice
from "./pages/SearchInvoice";

import CreateSupportingFile
from "./pages/CreateSupportingFile";

function App() {

    return (

        <BrowserRouter>

            <Routes>

                <Route
                    path="/"
                    element={
                        <Login />
                    }
                />

                <Route
                    path="/dashboard"
                    element={
                        <Dashboard />
                    }
                />

                <Route
                    path="/search"
                    element={
                        <SearchInvoice />
                    }
                />

                <Route
                    path="/create"
                    element={
                        <CreateSupportingFile />
                    }
                />

            </Routes>

        </BrowserRouter>
    );
}

export default App;
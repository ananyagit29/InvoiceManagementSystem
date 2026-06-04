import { useNavigate }
from "react-router-dom";

function Dashboard() {

    const navigate =
        useNavigate();

    const logout = () => {

        localStorage.clear();

        navigate("/");
    };

    return (

        <div>

            <h1>
                Invoice Document Management System
            </h1>

            <button
                onClick={() =>
                    navigate(
                        "/search"
                    )
                }
            >
                Search
            </button>

            {" "}

            <button
                onClick={() =>
                    navigate(
                        "/create"
                    )
                }
            >
                Create
            </button>

            {" "}

            <button
                onClick={logout}
            >
                Logout
            </button>

        </div>
    );
}

export default Dashboard;
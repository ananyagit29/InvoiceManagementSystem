import { useState } from "react";
import { useNavigate } from "react-router-dom";
import api from "../services/api";

function Login() {

    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");

    const navigate = useNavigate();

    const handleLogin = async () => {

        try {

            const response = await api.post(
                "/auth/login",
                {
                    username,
                    password
                }
            );

            if (response.data === "Login Success") {
                alert(response.data);
                navigate("/dashboard");
            } else {
                alert(response.data);
            }

        } catch (error) {
            alert("Invalid User");
        }
    };

    return (
        <div>

            <h2>Login</h2>

            <input
                type="text"
                placeholder="Username"
                value={username}
                onChange={(e) =>
                    setUsername(e.target.value)}
            />

            <br />
            <br />

            <input
                type="password"
                placeholder="Password"
                value={password}
                onChange={(e) =>
                    setPassword(e.target.value)}
            />

            <br />
            <br />

            <button onClick={handleLogin}>
                Login
            </button>

        </div>
    );
}

export default Login;
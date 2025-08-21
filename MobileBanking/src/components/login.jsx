import { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import {GoogleLogin, googleLogout} from "@react-oauth/google";

export default function Login() {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const navigate = useNavigate();

    const handleLogin = async () => {
        try {
            const res = await axios.post("http://localhost:8080/api/customers/login", {
                username,
                password
            });
            localStorage.setItem("user", JSON.stringify({ ...res.data, password }));

            if (res.data.role === "ADMIN") navigate("/admin");
            else navigate("/user");
        } catch (err) {
            alert(err.response?.data || "Login failed");
        }
    };

    const handleGoogleLogin = async () => {
        window.location.href = "http://localhost:8080/oauth2/authorization/google";

    };

    return (
        <div style={{ padding: "50px" }}>
            <h2>Login</h2>
            <input
                placeholder="Username"
                value={username}
                onChange={e => setUsername(e.target.value)}
            /><br/>
            <input
                placeholder="Password"
                type="password"
                value={password}
                onChange={e => setPassword(e.target.value)}
            /><br/>
            <button onClick={handleLogin}>Login</button>
            <hr/>
            <GoogleLogin
                onSuccess={handleGoogleLogin}
                onError={() => alert("Google login failed")}
            />
        </div>
    );
}

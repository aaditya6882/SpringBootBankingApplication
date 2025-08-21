import { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";

export default function Oauth2Redirect() {
    const navigate = useNavigate();

    useEffect(() => {
        const fetchUser = async () => {
            try {
                const res = await axios.get("http://localhost:8080/api/customers/oauth2/success", { withCredentials: true });
                localStorage.setItem("user", JSON.stringify(res.data));

                if (res.data.role === "ADMIN") navigate("/admin");
                else navigate("/user");
            } catch (err) {
                alert(err.response?.data || "Google login failed");
            }
        };
        fetchUser();
    }, []);

    return <div>Logging in...</div>;
}

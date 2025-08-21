import {Balance} from "./Balance.jsx";
import {Transaction} from "./Transaction.jsx";
import { useNavigate } from "react-router-dom";

export default function UserDashboard() {
        const navigate = useNavigate();
        const handleLogout = () => {
                localStorage.removeItem("user");
                navigate("/");
        };
        return (
            <div style={{ padding: "50px" }}>
                <h2>User Dashboard</h2>
                <Balance />
                <Transaction />

                <button onClick={handleLogout}>LogOut</button>
            </div>
        );
}

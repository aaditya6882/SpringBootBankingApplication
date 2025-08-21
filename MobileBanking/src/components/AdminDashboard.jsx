import CreateAccount from "./CreateAccount";
import Update from "./update.jsx";
import SearchUser from "./SearchAccount.jsx";
import {DepositWithDraw} from "./DepositWithDraw.jsx";
import {EnableDisableAccount} from "./EnableDisableAccount.jsx";
import { useNavigate } from "react-router-dom";
export default function AdminDashboard() {
    const navigate = useNavigate();
    const handleLogout = () => {
        localStorage.removeItem("user");
        navigate("/");
    };
    return (
        <div style={{ padding: "50px" }}>
            <h2>Admin Dashboard</h2>
            <CreateAccount />
            <Update />
            <SearchUser/>
            <DepositWithDraw/>
            <EnableDisableAccount/>
            <button onClick={handleLogout}>LogOut</button>
        </div>
    );
}

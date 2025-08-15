import { useState } from "react";
import axios from "axios";

export function EnableDisableAccount() {
    const [accountNumber, setAccountNumber] = useState("");
    const admin = JSON.parse(localStorage.getItem("user"));
    const adminUsername = admin?.username;
    const adminPassword = admin?.password;

    const handleDisable = async () => {
        try {
            await axios.put(`http://localhost:8080/api/customers/${accountNumber}/disable`, {}, {
                auth: { username: adminUsername, password: adminPassword },
                headers: { "Content-Type": "application/json" }
            });
            alert("Account disabled successfully");
        } catch (err) {
            alert(err.response?.data || "Failed to disable account");
        }
    };

    const handleEnable = async () => {
        try {
            await axios.put(`http://localhost:8080/api/customers/${accountNumber}/enable`, {}, {
                auth: { username: adminUsername, password: adminPassword },
                headers: { "Content-Type": "application/json" }
            });
            alert("Account enabled successfully");
        } catch (err) {
            alert(err.response?.data || "Failed to enable account");
        }
    };

    return (
        <div style={{ padding: "50px" }}>
            <h2>Enable/Disable Account</h2>
            <input placeholder="Account Number" value={accountNumber} onChange={e => setAccountNumber(e.target.value)} /><br />
            <button onClick={handleDisable}>Disable Account</button>
            <button onClick={handleEnable}>Enable Account</button>
        </div>
    );
}

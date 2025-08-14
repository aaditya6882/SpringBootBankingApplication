import { useState } from "react";
import axios from "axios";

export default function UpdateAccount() {
    const [accountNumber, setAccountNumber] = useState("");
    const [firstName, setFirstName] = useState("");
    const [lastName, setLastName] = useState("");
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");

    const fetchAccount = async () => {
        try {
            const res = await axios.get(`http://localhost:8080/api/customers/${accountNumber}/search`, {
                headers: { "Content-Type": "application/json" }
            });
            setFirstName(res.data.firstName || "");
            setLastName(res.data.lastName || "");
            setUsername(res.data.username || "");
        } catch (err) {
            alert("Failed to fetch account info");
        }
    };

    const handleUpdate = async () => {
        try {
            await axios.put(`http://localhost:8080/api/customers/${accountNumber}`, {
                firstName,
                lastName,
                username,
                password
            }, {
                headers: { "Content-Type": "application/json" }
            });
            alert("Account updated successfully!");
        } catch (err) {
            alert(err.response?.data || "Failed to update account");
        }
    };

    return (
        <div style={{ padding: "50px" }}>
            <input
                placeholder="Enter Account Number"
                value={accountNumber}
                onChange={e => setAccountNumber(e.target.value)}
            />
            <button onClick={fetchAccount}>Fetch Account</button>
            <h2>Update Account</h2>
            <input placeholder="First Name" value={firstName} onChange={e => setFirstName(e.target.value)} /><br />
            <input placeholder="Last Name" value={lastName} onChange={e => setLastName(e.target.value)} /><br />
            <input placeholder="Username" value={username} onChange={e => setUsername(e.target.value)} /><br />
            <input placeholder="Password" type="password" value={password} onChange={e => setPassword(e.target.value)} /><br />
            <button onClick={handleUpdate}>Update Account</button>
        </div>
    );
}

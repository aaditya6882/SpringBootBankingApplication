import { useState } from "react";
import axios from "axios";

export default function SearchUser() {
    const [accountNumber, setaccountNumber] = useState("");
    const [userData, setUserData] = useState(null);

    const handleSearch = async () => {
        try {
            const res = await axios.get(`http://localhost:8080/api/customers/${accountNumber}/search`, {
                headers: { "Content-Type": "application/json" }
            });
            setUserData(res.data);
        } catch (err) {
            alert(err.response?.data || "User not found");
            setUserData(null);
        }
    };

    return (
        <div style={{ padding: "50px" }}>
            <h2>Search User</h2>
            <input placeholder="Enter AccountNumber" value={accountNumber} onChange={e => setaccountNumber(e.target.value)} /><br />
            <button onClick={handleSearch}>Search</button>

            {userData && (
                <div style={{ marginTop: "20px" }}>
                    <h3>User Info:</h3>
                    <p>First Name: {userData.firstName}</p>
                    <p>Last Name: {userData.lastName}</p>
                    <p>Username: {userData.username}</p>
                    <p>Account Number: {userData.accountNumber}</p>
                    <p>Balance: {userData.balance}</p>
                    <p>Status: {userData.isActive ? "Active" : "Disabled"}</p>
                </div>
            )}
        </div>
    );
}

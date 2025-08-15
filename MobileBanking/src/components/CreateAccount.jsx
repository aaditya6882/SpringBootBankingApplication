import { useState } from "react";
import axios from "axios";

export default function CreateAccount() {
    const [accountNumber, setAccountNumber] = useState("");
    const [firstName, setFirstName] = useState("");
    const [lastName, setLastName] = useState("");
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [role, setRole] = useState("CUSTOMER");

    const handleCreate = async () => {
        try {
            const res = await axios.post("http://localhost:8080/api/customers/createAccount", {
                accountNumber,
                firstName,
                lastName,
                username,
                password,
                balance: 0,
                role,
            },
                {
                    auth: { username: admin.username, password: admin.password } 
                });
            alert("Account created successfully!");

        } catch (err) {
            console.log(err);
            alert(err.response?.data || "Failed to create account");
        }
    };

    return (
        <div style={{ padding: "50px" }}>
            <h2>Create Account</h2>
            <input placeholder="Account Number" value={accountNumber} onChange={e => setAccountNumber(e.target.value)} /><br />
            <input placeholder="First Name" value={firstName} onChange={e => setFirstName(e.target.value)} /><br />
            <input placeholder="Last Name" value={lastName} onChange={e => setLastName(e.target.value)} /><br />
            <input placeholder="Username" value={username} onChange={e => setUsername(e.target.value)} /><br />
            <input placeholder="Password" type="password" value={password} onChange={e => setPassword(e.target.value)} /><br />
            <select value={role} onChange={e => setRole(e.target.value)}>
                <option value="CUSTOMER">Customer</option>
            </select><br />
            <button onClick={handleCreate}>Create Account</button>
        </div>
    );
}
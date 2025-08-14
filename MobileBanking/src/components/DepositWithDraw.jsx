import axios from "axios";
import { useState } from "react";

export function DepositWithDraw() {
    const [username, setUsername] = useState("");
    const [accountNumber, setAccountNumber] = useState("");
    const [amount, setAmount] = useState(0);

    const deposit = async () => {
        try {
            await axios.post(`http://localhost:8080/api/transactions/deposit`, null, {
                params: { userName: username, accNum: accountNumber, amount }

            });
            alert("Deposit successful");
        } catch (err) {
            alert(err.response?.data || "Error");
        }
    };

    const withdraw = async () => {
        try {
            await axios.post(`http://localhost:8080/api/transactions/withdraw`, null, {
                params: { userName: username, accNum: accountNumber, amount }

            });
            alert("Withdraw successful");
        } catch (err) {
            alert(err.response?.data || "Error");
        }
    };

    return (
        <>
            <h3>Admin Deposit / Withdraw</h3>
            <input
                type="text"
                value={username}
                onChange={e => setUsername(e.target.value)}
                placeholder="Username"
            /><br />
            <input
                type="text"
                value={accountNumber}
                onChange={e => setAccountNumber(e.target.value)}
                placeholder="Account Number"
            /><br />
            <input
                type="number"
                value={amount}
                onChange={e => setAmount(e.target.value)}
                placeholder="Amount"
            /><br />
            <button onClick={deposit}>Deposit</button>
            <button onClick={withdraw}>Withdraw</button>
        </>
    );
}

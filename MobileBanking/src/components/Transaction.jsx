import axios from "axios";
import { useEffect, useState } from "react";

export function Transaction() {
    const user = JSON.parse(localStorage.getItem("user"));
    const userName = user?.username;
    const password=user.password;
    const accNum = user?.accountNumber;
    const [transaction, setTransaction] = useState([]);

    const fetchTransaction = async () => {
        try {
            const res = await axios.get("http://localhost:8080/api/transactions/history",
                {
                    auth: { username: userName, password },
                    params: { userName, accNum }
            });
            setTransaction(res.data);
        } catch (error) {
            console.error("Error fetching transactions:", error);
        }
    };

    useEffect(() => {
        fetchTransaction();
    }, []);

    return (
        <>
            <h2>Transaction History</h2>
            <table>
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Type</th>
                    <th>Amount</th>
                    <th>Date</th>
                </tr>
                </thead>
                <tbody>
                {transaction.map((tx) => (
                    <tr key={tx.id}>
                        <td>{tx.id}</td>
                        <td>{tx.type}</td>
                        <td>{tx.amount}</td>
                        <td>{new Date(tx.createdAt).toLocaleString()}</td>
                    </tr>
                ))}
                </tbody>
            </table>
        </>
    );
}

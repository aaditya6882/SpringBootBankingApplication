import { useEffect, useState } from "react";

export default function UserDashboard() {
    const [balance, setBalance] = useState(0);
    const [transactions, setTransactions] = useState([]);

    useEffect(() => {
        // For now, mock data
        setBalance(5000);
        setTransactions([
            { id: 1, type: "Deposit", amount: 1000, date: "2025-08-13" },
            { id: 2, type: "Withdrawal", amount: 500, date: "2025-08-12" },
        ]);
    }, []);

    return (
        <div style={{ padding: "50px" }}>
            <h2>User Dashboard</h2>
            <p>Balance: {balance}</p>

            <h3>Transactions</h3>
            <table border="1" cellPadding="10">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Type</th>
                    <th>Amount</th>
                    <th>Date</th>
                </tr>
                </thead>
                <tbody>
                {transactions.map(tx => (
                    <tr key={tx.id}>
                        <td>{tx.id}</td>
                        <td>{tx.type}</td>
                        <td>{tx.amount}</td>
                        <td>{tx.date}</td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
}

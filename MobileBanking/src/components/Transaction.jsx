import axios from "axios";
import {useState} from "react";
export function Transaction(){
    const user=JSON.parse(localStorage.getItem("user"))
    const username=user?.username;
    const accNum = user?.accountNumber;
    const [transaction, settransaction]=useState([])
    const fetchTransaction= async ()=>{
        try {
            const res = await (axios.get("http://localhost:8080/api/transactions/history"), {params: {username, accNum}})
            settransaction(res.data)
        }catch (error){
            console.log(error)
        }
    }
    return(
        <>
            <h2>Transaction History</h2>
            <table>
                <thead>
                <tr><th>ID</th><th>Type</th><th>Amount</th><th>Date</th></tr>
                </thead>
                <tbody>
                {transaction.map(tx => (
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
    )
}
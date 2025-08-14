import {useEffect, useState} from "react";
import axios from "axios";
export function Balance() {
    const [balance, setBalance] = useState(0);
    const user = JSON.parse(localStorage.getItem("user"));
    const username = user?.username;
    console.log("Username from localStorage:", username);
    const fetchBalance = async () => {
        try {
            const res = await axios.get(`http://localhost:8080/api/customers/${username}/balance`);
            setBalance(res.data);
        } catch (err) {
            console.log(err);
        }
    };

    useEffect(() => {
        fetchBalance();
    }, []);
    return (
        <div style={{padding: "50px"}}>
            <h2>Balance</h2>
            <p>Balance: {balance}</p>
        </div>
    );
}
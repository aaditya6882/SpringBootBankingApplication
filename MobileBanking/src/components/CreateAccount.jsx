import { useState } from "react";

export default function CreateAccount() {
    const [account, setAccount] = useState({
        accountNumber: "",
        firstName: "",
        lastName: "",
        username: "",
        password: "",
        balance: 0,
        role: "CUSTOMER"
    });

    const handleChange = (e) => {
        setAccount({ ...account, [e.target.name]: e.target.value });
    };

    const handleSubmit = () => {
        console.log("Create account data", account);
        alert("Account would be created (mock)");
    };

    return (
        <div>
            <h3>Create Customer Account</h3>
            <input name="accountNumber" placeholder="Account Number" onChange={handleChange} /><br/>
            <input name="firstName" placeholder="First Name" onChange={handleChange} /><br/>
            <input name="lastName" placeholder="Last Name" onChange={handleChange} /><br/>
            <input name="username" placeholder="Username" onChange={handleChange} /><br/>
            <input name="password" placeholder="Password" type="password" onChange={handleChange} /><br/>
            <input name="balance" placeholder="Balance" type="number" onChange={handleChange} /><br/>
            <button onClick={handleSubmit}>Create Account</button>
        </div>
    );
}

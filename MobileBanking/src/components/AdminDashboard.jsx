import { useState } from "react";
import CreateAccount from "./CreateAccount";

export default function AdminDashboard() {
    return (
        <div style={{ padding: "50px" }}>
            <h2>Admin Dashboard</h2>
            <CreateAccount />
        </div>
    );
}

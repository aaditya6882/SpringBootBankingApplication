import { useState } from 'react'
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import './App.css'
import Login from "./components/login.jsx";
import UserDashboard from "./components/UserDashboard.jsx";
import AdminDashboard from "./components/AdminDashboard.jsx";

function App() {
    return (
        <Router>
            <Routes>
                <Route path="/" element={<Login />} />
                <Route path="/user" element={<UserDashboard />} />
                <Route path="/admin" element={<AdminDashboard />} />
            </Routes>
        </Router>
    );


}

export default App

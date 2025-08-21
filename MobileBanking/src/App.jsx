import './App.css';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Login from "./components/login.jsx";
import UserDashboard from "./components/UserDashboard.jsx";
import AdminDashboard from "./components/AdminDashboard.jsx";
import Oauth2Redirect from "./components/Oauth2Redirect.jsx";
function App() {
    return (
        <Router>
            <Routes>
                <Route path="/" element={<Login />} />
                <Route path="/admin" element={<AdminDashboard />} />
                <Route path="/oauth2/redirect" element={<Oauth2Redirect />} />
                <Route path="/user" element={<UserDashboard />} />
            </Routes>
        </Router>
    );


}

export default App

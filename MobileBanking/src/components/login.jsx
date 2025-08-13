// import { useState } from 'react';
// import { useNavigate } from 'react-router-dom';

// export default function Login() {
//     const [username, setUsername] = useState('');
//     const [password, setPassword] = useState('');
//     const navigate = useNavigate();
//
//     const handleLogin = () => {
//         if (username === 'admin' && password === 'admin123') {
//             navigate('/admin');
//         } else {
//             navigate('/user');
//         }
//     };
//
//     return (
//         <div style={{ padding: '50px' }}>
//             <h2>Login</h2>
//             <input
//                 placeholder="Username"
//                 value={username}
//                 onChange={e => setUsername(e.target.value)}
//             /><br/>
//             <input
//                 placeholder="Password"
//                 type="password"
//                 value={password}
//                 onChange={e => setPassword(e.target.value)}
//             /><br/>
//             <button onClick={handleLogin}>Login</button>
//         </div>
//     );
// }
import { useState } from "react";
import { useNavigate } from "react-router-dom";
// import { GoogleLogin } from "@react-oauth/google";

export default function login() {

    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const navigate = useNavigate();

    const handleLogin = () => {
        // For now, simple mock logic
        if (username === "admin" && password === "admin123") {
            navigate("/admin");
        } else {
            navigate("/user");
        }
    };

    const handleGoogleLogin = (credentialResponse) => {
        // Mock login: navigate to user dashboard after Google login
        navigate("/user");
    };

    return (
        <div style={{ padding: "50px" }}>
            <h2>Login</h2>
            <input
                placeholder="Username"
                value={username}
                onChange={e => setUsername(e.target.value)}
            /><br/>
            <input
                placeholder="Password"
                type="password"
                value={password}
                onChange={e => setPassword(e.target.value)}
            /><br/>
            <button onClick={handleLogin}>Login</button>
            <hr />
            {/*<GoogleLogin*/}
            {/*    onSuccess={handleGoogleLogin}*/}
            {/*    onError={() => alert("Google login failed")}*/}
            {/*/>*/}
        </div>
    );
}

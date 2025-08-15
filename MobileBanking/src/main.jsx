import { GoogleOAuthProvider } from '@react-oauth/google';
import App from './App.jsx';
import { StrictMode } from 'react';
import { createRoot } from 'react-dom/client';

const clientId = '262901389171-e7tjssf2iirsdn3n2a85r5e7sh08nl9e.apps.googleusercontent.com';

createRoot(document.getElementById('root')).render(
    <StrictMode>
        <GoogleOAuthProvider clientId={clientId}>
            <App />
        </GoogleOAuthProvider>
    </StrictMode>
);

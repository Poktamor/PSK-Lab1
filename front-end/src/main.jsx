import {StrictMode} from 'react'
import {createRoot} from 'react-dom/client'

import 'bootstrap/dist/css/bootstrap.min.css';
import './index.css'
import Home from './People.jsx'
import Nav from "./Nav.jsx";
import {BrowserRouter, Route, Routes} from "react-router-dom";
import People from "./People.jsx";
import Submarines from "./Submarines.jsx";
import Jobs from "./Jobs.jsx";

createRoot(document.getElementById('root')).render(
    <StrictMode>
        <BrowserRouter>
            <Routes>
                <Route element={<Nav />}>
                    <Route path="/" element={<People />} />
                    <Route path="/people" element={<People />} />
                    <Route path="/submarines" element={<Submarines />} />
                    <Route path="/jobs" element={<Jobs />} />
                </Route>
            </Routes>
        </BrowserRouter>
    </StrictMode>
);


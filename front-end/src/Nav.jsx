import {Outlet} from "react-router-dom";
import Navbar from "./Navbar.jsx";

function Nav() {
    return (
        <>
            <Navbar/>
            <Outlet/>
        </>
    )
}

export default Nav
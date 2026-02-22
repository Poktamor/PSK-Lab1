import {Nav} from "react-bootstrap";
import {Link} from "react-router-dom";

function Navbar() {
    return(
        <Nav variant="tabs" className="w-100 largeText mb-1">
            <Nav.Item>
                <Nav.Link as={Link} to="/submarines">
                    Submarines
                </Nav.Link>
            </Nav.Item>
            <Nav.Item>
                <Nav.Link as={Link} to="/people">
                    People
                </Nav.Link>
            </Nav.Item>
            <Nav.Item>
                <Nav.Link as={Link} to="/jobs">
                    Jobs
                </Nav.Link>
            </Nav.Item>
        </Nav>
    )
}

export default Navbar;
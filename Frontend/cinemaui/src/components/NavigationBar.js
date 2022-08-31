import React from 'react';

import { Navbar, Nav } from 'react-bootstrap';
import {Link} from 'react-router-dom';

class NavigationBar extends React.Component {
    render() {
        return (
            
            <Navbar bg="dark" variant="dark">
                <Link to={""} className='navbar-brand'>
                <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/9/98/Cinema_City.svg/2560px-Cinema_City.svg.png" width="50" height="25" alt=""></img>
            </Link>
                    <Nav className="me-auto">
                        <Link to={"add"} className="nav-link">Add Movie</Link>
                        <Link to={"list"} className="nav-link">Get All Movies</Link>
                        <Link to={"profile"} className="nav-link">Profile</Link>
                        <Link to={"register"} className="nav-link">Register</Link>
                        <Link to={"login"} className="nav-link">Login</Link>
                        <Link to={"logout"} className="nav-link">Logout</Link>
                    </Nav>
            </Navbar>
        )
    }
}

export default NavigationBar;
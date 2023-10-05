import React, {Component, useState} from 'react';
import {Navbar, NavbarBrand} from 'reactstrap';
import {Link} from 'react-router-dom';

export default function AppNavbar(props) {
  
    const {isOpen, setIsOpen} = useState(false);
   //this.toggle = this.toggle.bind(this);
   function toggle() {
        setIsOpen({
            isOpen: !this.state.isOpen
        });
    }
   
    return (<Navbar color="dark" dark expand="md">
            <NavbarBrand tag={Link} to="/">Home</NavbarBrand>
        </Navbar>);
    
}
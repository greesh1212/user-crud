import logo from './logo.svg';
import './App.css';
import { useState, useEffect } from 'react';
import { BrowserRouter,Route } from 'react-router-dom';
import Home from "./Home";
import UserEdit from './userEdit';
import Users from './users';



function App() {
  return (
    <BrowserRouter>
            <Route path='/' exact={true} component={Users}/>
            <Route path='/users' exact={true} component={Users}/>
            <Route path='/users/:id' component={UserEdit}/>
    </BrowserRouter>
  );
}

export default App;

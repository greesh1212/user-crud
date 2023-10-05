import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { Link, withRouter } from 'react-router-dom';
import { Button, Container, Form, FormGroup, Input, Label } from 'reactstrap';

function UserEdit(props){
const [state, setState] =useState({});
const routeParams = useParams();
let emptyUser = {firstName:'', lastName:'', email:'', status:'', phoneNo:'', dob:'', address:'' };
useEffect(()=>{
  if(routeParams.id=='new'){
    setState(emptyUser);
  } else {
    fetch("/api/user/"+routeParams.id).then((res)=> res.json())
    .then(j=>{setState(j)});
  }

},[]);
 const title = <h2>{ routeParams.id !='new'? 'Edit User' : 'Add User'}</h2>;
        
         function handleSubmit(event) {
          event.preventDefault();
          let url= routeParams.id!='new' ?'/api/user/update':'/api/user/create';
          let method = routeParams.id!='new' ?'PUT':'POST';
          fetch(url,{ method:method, headers:{
        	'Accept':'application/json',
        	'Content-Type':'application/json'
        	},
          body:JSON.stringify(state)}).then((res)=>{ debugger; res.json()})

          }
          function handleChange(event) {
            const target = event.target;
            const value = target.value;
            const name = target.name;
            let item = {...state};
            item[name] = value;
            setState(item);
        }
    
        return( <div>
            <Container>
                {title}
                <Form onSubmit={handleSubmit}>
                    <FormGroup>
                        <Label for="name">First Name</Label>
                        <Input type="text" name="firstName" id="nfirstNameame" value={state.firstName || ''}
                               onChange={handleChange} autoComplete="firstName"/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="name">Last Name</Label>
                        <Input type="text" name="lastName" id="lastName" value={state.lastName || ''}
                               onChange={handleChange} autoComplete="lastName"/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="email">Email</Label>
                        <Input type="text" name="email" id="email" value={state.email || ''}
                               onChange={handleChange} autoComplete="email"/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="status">Status</Label>
                        <Input type="text" name="status" id="status" value={state.status || ''}
                               onChange={handleChange} autoComplete="status"/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="phoneNo">phone number</Label>
                        <Input type="text" name="phoneNo" id="phoneNo" value={state.phoneNo || ''}
                               onChange={handleChange} autoComplete="phoneNo"/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="dob">Date Of Birth</Label>
                        <Input type="date" name="dob" id="dob" value={state.dob || ''}
                               onChange={handleChange} autoComplete="dateOfBirth"/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="address">Address</Label>
                        <Input type="text" name="address" id="Address" value={state.address || ''}
                               onChange={handleChange} autoComplete="address"/>
                    </FormGroup>
                    <FormGroup>
                        <Button color="primary" type="submit">Save</Button>{' '}
                        <Button color="secondary" tag={Link} to="/users">Cancel</Button>
                    </FormGroup>
                </Form>
            </Container>
        </div>)
  }
  export default UserEdit;
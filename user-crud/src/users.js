import { useState, useEffect } from 'react';
import { Button, ButtonGroup, Container, Table, Input } from 'reactstrap';
import { Link } from 'react-router-dom';
import AppNavbar from './AppNavbar';
function Users(){

    const [state, setState] = useState([]);
    const [searchName, setSearchName] = useState("");
    useEffect(() => {
        if(!searchName){
            fetch("/api/user/all").then((res)=> res.json())
            .then(j=>{setState(j)});
        }
       
    },[]);
    const remove=(id)=>{
    	fetch("/api/user/delete/"+id,{method:'DELETE'}).then((res)=>alert("User deleted with Id : "+id))
    }
    const onChangeSearchName = e => {
        const searchName= e.target.value;
        setSearchName(searchName);
    };
    const findByName = () => {
        let result = state.filter(e => (e.firstName===searchName || e.lastName===searchName) )
        setState(result);
      };
    const users = state.map(emp => {
        return (<tr key={emp.id}>
            <td>{emp.firstName}</td>
            <td >{emp.lastName}</td>
            <td>{emp.email}</td>
            <td>{emp.status}</td>
            <td>{emp.phoneNo}</td>
            <td style={{whiteSpace: 'nowrap'}}>{emp.dob}</td>
            <td>{emp.address}</td>
            <td>
                <ButtonGroup>
                    <Button size="sm" color="primary" tag={Link} to={"/users/" + emp.id}>Edit</Button>
                    <Button size="sm" color="danger" onClick={() => remove(emp.id)}>Delete</Button>
                </ButtonGroup>
            </td>
        </tr>)
    });

    return (
        <div>
        <Container fluid>
            <div className="float-right">
            <div className="input-group mb-3">
          <input
            type="text"
            className="form-control"
            placeholder="Search by title"
            value={searchName}
            onChange={onChangeSearchName}
          />
          <div className="input-group-append">
            <button
              className="btn btn-outline-secondary"
              type="button"
              onClick={findByName}
            >
              Search
            </button>
          </div>
        </div>
                                <Button color="success" tag={Link} to="/users/new">Add User</Button>
            </div>
            <h3>Users</h3>
            <Table className="mt-4">
                <thead>
                <tr>
                    <th width="13%">First Name</th>
                    <th width="13%">Last Name</th>
                    <th width="13%">Email</th>
                    <th width="13%">Status</th>
                    <th width="13%">Phone</th>
                    <th width="13%">DOB</th>
                    <th width="13%">Address</th>
                    <th width="9%">Actions</th>

                </tr>
                </thead>
                <tbody>
                {users}
                </tbody>
            </Table>
        </Container>
    </div>
    );
}
 export default Users;
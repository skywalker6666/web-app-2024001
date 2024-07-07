import React, { useEffect, useState } from 'react'
import { listEmployees } from '../services/EmployeeService'
import { useNavigate } from 'react-router-dom'
import ImageComponent from './ImageComponent'
import FileUploadComponent from './FileUploadComponent'
const imageUrl='http://127.0.0.1:8080/api/image/fileSystem/104056451_p0_master1200.jpg'
const ListEmployeeComponent = () => {
    const [employees, setEmployees] = useState([])
    const navigator = useNavigate();
    useEffect(() => {
        listEmployees().then((response) => {
            setEmployees(response.data);

        }).catch(error => {
            console.error(error);
        })

    }, [])
    function addNewEmployee() {
        navigator('/add-employee')
    }

    return (
        <div className='container'>
            <button className='btn-bd-primary' onClick={addNewEmployee}>Add Employee</button>
            <h2 className='text-center'>List Of Employees</h2>
                            
            
            <table className='table table-striped table-bordered'>
                <thead>
                    <tr>
                        <th>Employee Id</th>
                        <th>Employee First Name</th>
                        <th>Employee Last Name</th>
                        <th>Employee Email</th>
                        <th>Employee Image</th>
                        
                    </tr>
                </thead>
                <tbody>
                    {
                        employees.map(employee =>
                            <tr key={employee.id}>
                                <td>{employee.id}</td>
                                <td>{employee.firstName}</td>
                                <td>{employee.lastName}</td>
                                <td>{employee.email}</td>
                                <td><FileUploadComponent/></td>
                            </tr>
                        )
                    }
                </tbody>
            </table>


        </div>
    )
}

export default ListEmployeeComponent
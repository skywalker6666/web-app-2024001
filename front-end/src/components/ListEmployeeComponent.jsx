import React, { useEffect, useState } from 'react';
import { listEmployees, getImage, uploadImage } from '../services/EmployeeService';
import { useNavigate } from 'react-router-dom';
import ImageComponent from './ImageComponent';

const ListEmployeeComponent = () => {
    const [employees, setEmployees] = useState([]);
    const [selectedImage, setSelectedImage] = useState(null);
    const [selectedEmployeeId, setSelectedEmployeeId] = useState(null);
    const [uploading, setUploading] = useState(false);
    const navigate = useNavigate();

    useEffect(() => {
        listEmployees().then((response) => {
            setEmployees(response.data);
        }).catch(error => {
            console.error(error);
        });
    }, []);

    const addNewEmployee = () => {
        navigate('/add-employee');
    };

    const onChangeHandler = (event, employeeId) => {
        setSelectedImage(event.target.files[0]);
        setSelectedEmployeeId(employeeId);
    };

    const onClickHandler = () => {
        if (!selectedImage || !selectedEmployeeId) {
            console.error('No image selected or no employee selected.');
            return;
        }
        setUploading(true);
        uploadImage(selectedEmployeeId, selectedImage).then(() => {
            console.log('File uploaded successfully!');
            setSelectedImage(null);
            setUploading(false);
            getImage(selectedEmployeeId).then(response => {
                setEmployees(prevEmployees => prevEmployees.map(employee =>
                    employee.id === selectedEmployeeId ? { ...employee, imageUrl: response.data.url } : employee
                ));
            }).catch(error => {
                console.error('Error fetching image:', error);
            });
        }).catch(error => {
            console.error('Error uploading file:', error);
            setUploading(false);
        });
    };

    return (
        <div className='container'>
            <button className='btn btn-primary' onClick={addNewEmployee}>Add Employee</button>
            <h2 className='text-center'>List Of Employees</h2>
            <table className='table table-striped table-bordered'>
                <thead>
                    <tr>
                        <th>Employee Id</th>
                        <th>Employee First Name</th>
                        <th>Employee Last Name</th>
                        <th>Employee Email</th>
                        <th>Employee Image</th>
                        <th>Upload Image</th>
                    </tr>
                </thead>
                <tbody>
                    {employees.map(employee => (
                        <tr key={employee.id}>
                            <td>{employee.id}</td>
                            <td>{employee.firstName}</td>
                            <td>{employee.lastName}</td>
                            <td>{employee.email}</td>
                            <td><ImageComponent imageUrl={`http://127.0.0.1:8080/api/employees/${employee.id}/image`} /></td>
                            <td>
                                <input type="file" onChange={(event) => onChangeHandler(event, employee.id)} />
                                <button onClick={onClickHandler} disabled={uploading}>Upload</button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
};

export default ListEmployeeComponent;

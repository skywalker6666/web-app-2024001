package backend.service;

import backend.dao.EmployeeDao;

public interface EmployeeService  {
    EmployeeDao createEmployee(EmployeeDao employeeDao);
}

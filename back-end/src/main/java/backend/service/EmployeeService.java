package backend.service;

import backend.dao.EmployeeDao;

import java.util.List;

public interface EmployeeService  {
    EmployeeDao createEmployee(EmployeeDao employeeDao);
    EmployeeDao getEmployeeById(Long employeeId);
    List<EmployeeDao> getAllEmployees();
    EmployeeDao updateEmployee(Long employeeId,EmployeeDao employeeDao);
    void deleteEmployee(Long employeeId);
}

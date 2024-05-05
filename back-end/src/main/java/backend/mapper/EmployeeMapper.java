package backend.mapper;

import backend.entity.Employee;
import backend.dao.EmployeeDao;

public class EmployeeMapper {
    public static EmployeeDao mapToEmployeeDao(Employee employee){
        return new EmployeeDao(
                employee.getId(), employee.getFirstName(), employee.getLastName(), employee.getEmail()
        );
    }
    public static Employee mapToEmployeeDao(EmployeeDao employeeDao){
        return new Employee(
                employeeDao.getId(),  employeeDao.getFirstName(),  employeeDao.getLastName(),  employeeDao.getEmail()
        );
    }
}

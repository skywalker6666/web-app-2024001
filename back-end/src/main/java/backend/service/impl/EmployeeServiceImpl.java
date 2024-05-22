package backend.service.impl;

import backend.dao.EmployeeDao;
import backend.entity.Employee;
import backend.exception.ResourceNotFoundException;
import backend.mapper.EmployeeMapper;
import backend.repository.EmployeeRepository;
import backend.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;
    @Override
    public EmployeeDao createEmployee(EmployeeDao employeeDao){
        Employee employee= EmployeeMapper.mapToEmployee(employeeDao);
        Employee savedEmployee =employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDao(savedEmployee);
    }

    @Override
    public EmployeeDao getEmployeeById(Long employeeId) {
        employeeRepository.findById(employeeId).orElseThrow(()->new ResourceNotFoundException("Employee is not empty"));
        return null;
    }

}

package backend.service.impl;

import backend.dao.EmployeeDao;
import backend.entity.Employee;
import backend.exception.ResourceNotFoundException;
import backend.mapper.EmployeeMapper;
import backend.repository.EmployeeRepository;
import backend.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDao createEmployee(EmployeeDao employeeDao) {
        Employee employee = EmployeeMapper.mapToEmployee(employeeDao);
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDao(savedEmployee);
    }

    @Override
    public EmployeeDao getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException("Employee is not exists with given id:" + employeeId));
        return EmployeeMapper.mapToEmployeeDao(employee);
    }

    @Override
    public List<EmployeeDao> getAllEmployees() {
        List<Employee> employeeList = employeeRepository.findAll();
        return employeeList.stream().map(EmployeeMapper::mapToEmployeeDao).collect(Collectors.toList());
    }
}

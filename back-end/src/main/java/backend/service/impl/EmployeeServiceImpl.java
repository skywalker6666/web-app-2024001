package backend.service.impl;

import backend.dao.EmployeeDao;
import backend.entity.Employee;
import backend.entity.FileData;
import backend.exception.ResourceNotFoundException;
import backend.mapper.EmployeeMapper;
import backend.repository.EmployeeRepository;
import backend.repository.FileDataRepository;
import backend.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    private FileDataRepository fileDataRepository;
    private final String FOLDER_PATH = "D:/";

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
        List<Employee> employeeList = employeeRepository.findAll(Sort.by(Sort.Direction.ASC,"id"));
        return employeeList.stream().map(EmployeeMapper::mapToEmployeeDao).collect(Collectors.toList());
    }

    @Override
    public EmployeeDao updateEmployee(Long employeeId, EmployeeDao employeeDao) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException("Employee is not exists with given id:" + employeeId));
        employee.setFirstName(employeeDao.getFirstName());
        employee.setLastName(employeeDao.getLastName());
        employee.setEmail(employeeDao.getEmail());
        Employee updateEmployeeObj = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDao(updateEmployeeObj);
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        employeeRepository.deleteById(employeeId);
    }


    @Override
    public byte[] downloadEmployeeImage(String fileName) throws IOException {
        Optional<FileData> fileData = fileDataRepository.findByName(fileName);
        String filePath = fileData.get().getFilePath();
        byte[] images = Files.readAllBytes(new File(filePath).toPath());
        return images;
    }

    @Override
    @Transactional
    public void uploadEmployeeImage(Long id, String imageName) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));
        employee.setImageName(imageName);
        employeeRepository.save(employee);
    }
}

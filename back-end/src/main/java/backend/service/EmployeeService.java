package backend.service;

import backend.dao.EmployeeDao;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
@Service
public interface EmployeeService  {
    EmployeeDao createEmployee(EmployeeDao employeeDao);
    EmployeeDao getEmployeeById(Long employeeId);
    List<EmployeeDao> getAllEmployees();
    EmployeeDao updateEmployee(Long employeeId,EmployeeDao employeeDao);
    void deleteEmployee(Long employeeId);
    void uploadEmployeeImage(Long id, String imageName);

    byte[] downloadEmployeeImage(String fileName) throws IOException;

}

package backend.controller;

import backend.dao.EmployeeDao;
import backend.entity.Employee;
import backend.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    private EmployeeService employeeService;

    //Build ass employee rest api
    @PostMapping
    public ResponseEntity<EmployeeDao> createEmployee(@RequestBody EmployeeDao employeeDao) {
        EmployeeDao savedEmployee = employeeService.createEmployee(employeeDao);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @GetMapping("{Id}")
    public ResponseEntity<EmployeeDao> getEmployeeById(@PathVariable("Id") Long employeeId) {
        EmployeeDao employeeDao = employeeService.getEmployeeById(employeeId);
        return ResponseEntity.ok(employeeDao);
    }
    @GetMapping
    public ResponseEntity<List<EmployeeDao>> getAllEmployees() {
        List<EmployeeDao> employees=employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }
    @PutMapping("{Id}")
    public  ResponseEntity<EmployeeDao> updateEmployee(Long employeeId,EmployeeDao updateEmployee){
        EmployeeDao employeeDao=employeeService.updateEmployee(employeeId,updateEmployee);
        return ResponseEntity.ok(employeeDao);

    }

}

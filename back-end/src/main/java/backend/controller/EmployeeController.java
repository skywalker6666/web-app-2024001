package backend.controller;

import backend.dao.EmployeeDao;
import backend.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
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

    @GetMapping("{id}")
    public ResponseEntity<EmployeeDao> getEmployeeById(@PathVariable("id") Long employeeId) {
        EmployeeDao employeeDao = employeeService.getEmployeeById(employeeId);
        return ResponseEntity.ok(employeeDao);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDao>> getAllEmployees() {
        List<EmployeeDao> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    @PutMapping("{id}")
    public ResponseEntity<EmployeeDao> updateEmployee(@PathVariable("id") Long employeeId, @RequestBody EmployeeDao updateEmployee) {
        EmployeeDao employeeDao = employeeService.updateEmployee(employeeId, updateEmployee);
        return ResponseEntity.ok(employeeDao);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long employeeId) {
        employeeService.deleteEmployee(employeeId);
        return ResponseEntity.ok("employee deleted successfully!");
    }

}

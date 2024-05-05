package backend.controller;

import backend.dao.EmployeeDao;
import backend.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
private EmployeeService employeeService;
//Build ass employee rest api
    public ResponseEntity<EmployeeDao> createEmployee(@RequestBody EmployeeDao employeeDao){
        EmployeeDao  savedEmployee=employeeService.createEmployee( employeeDao);
        return new ResponseEntity<>(savedEmployee,HttpStatus.CREATED);
    }


}

package backend.controller;

import backend.dao.EmployeeDao;
import backend.entity.Employee;
import backend.service.EmployeeService;
import backend.service.ImageService;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @Value("${file.upload-dir}")
    private final String uploadDir="D:/user/Pictures";
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
    @PostMapping("/{id}/uploadImage")
    public ResponseEntity<String> uploadImage(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("File is empty");
        }

        try {
            // Save the file to the local file system
            byte[] bytes = file.getBytes();
            Path path = Paths.get(uploadDir + "/" + file.getOriginalFilename());
            Files.write(path, bytes);

            // Update employee's image name in the database
            employeeService.uploadEmployeeImage(id, file.getOriginalFilename());

            return ResponseEntity.ok("File uploaded successfully: " + file.getOriginalFilename());
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("File upload failed");
        }
    }
    @GetMapping("/{id}/image")
    public ResponseEntity<Resource> downloadImage(@PathVariable Long id){
        try {
            EmployeeDao employee = employeeService.getEmployeeById(id);
            String imageName = employee.getImageName();
            if (imageName!=null) {
                Path path = Paths.get(uploadDir).resolve(imageName).normalize();
                Resource resource = new UrlResource(path.toUri());

                if (!resource.exists()) {
                    return ResponseEntity.notFound().build();
                }

                return ResponseEntity.ok()
                        .contentType(MediaType.valueOf("image/png"))
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            }
            else
                return ResponseEntity.status(500).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }


}
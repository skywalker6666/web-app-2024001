package backend.repository;

import backend.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    @Override
    Optional<Employee> findById(Long id);
    Optional<Employee> findByImageName(String imageName);
}
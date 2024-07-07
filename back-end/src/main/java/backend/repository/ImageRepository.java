package backend.repository;

import backend.entity.EmployeeImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author alan9
 **/
@Repository
public interface ImageRepository extends JpaRepository<EmployeeImage, Long> {
    Optional<EmployeeImage> findByImageName(String fileName);
}

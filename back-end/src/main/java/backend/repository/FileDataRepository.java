package backend.repository;

import backend.entity.FileData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author alan9
 **/
public interface FileDataRepository extends JpaRepository<FileData,Integer> {
    Optional<FileData> findByName(String fileName);
}

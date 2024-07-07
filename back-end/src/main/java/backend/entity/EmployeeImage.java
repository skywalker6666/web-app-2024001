package backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author alan9
 **/
@Entity
@Table(name = "employee_image")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "type")
    private String type;
    @Column(name = "image_name")
    private String imageName;
    @Lob
    @Column(name = "image_data", length = 1000)
    private byte[] imageData;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }


    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(MultipartFile imageData) throws IOException {
        this.imageData = imageData.getBytes();
    }

    public EmployeeImage(Long id, String imageName, byte[] imageData) {
        this.id = id;
        this.imageName = imageName;
        this.imageData = imageData;
    }
}
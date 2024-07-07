package backend.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestParam;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDao {
    private long id;
    private String firstName;
    private String lastName;
    private String email;
}

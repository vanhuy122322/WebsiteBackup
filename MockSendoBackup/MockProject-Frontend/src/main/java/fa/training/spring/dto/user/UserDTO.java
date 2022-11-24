package fa.training.spring.dto.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import fa.training.spring.dto.AbstractDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class UserDTO extends AbstractDTO {

    private String fullName;
    private String username;
    private String phone;
    private String password;
    private String email;
    private String shopName;
    private String gender;
    private String location;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate birthDate;
    private List<String> roleNames = new ArrayList<>();
    private List<String> locations = new ArrayList<String>();

}

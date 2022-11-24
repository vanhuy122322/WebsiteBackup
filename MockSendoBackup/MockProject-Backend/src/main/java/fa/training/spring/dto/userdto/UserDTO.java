package fa.training.spring.dto.userdto;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import fa.training.spring.dto.AbstractDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class UserDTO extends AbstractDTO {

    private String fullName;
    private String phone;
    private String username;
    private String password;
    private String email;
    private String shopName;
    private String gender;
    private String location;
    private LocalDate birthDate;

    private Set<String> roleNames;
    private List<String> locations;

    public UserDTO(String fullName, String username, String email, String password) {
        // TODO Auto-generated constructor stub
        this.fullName = fullName;
        this.username = username;
        this.email = email;
        this.password = password;
    }
}

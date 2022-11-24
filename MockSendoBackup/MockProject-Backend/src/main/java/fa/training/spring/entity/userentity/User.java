package fa.training.spring.entity.userentity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.data.mongodb.core.mapping.Document;

import fa.training.spring.entity.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Document(collection = "users")
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class User extends AbstractEntity {
    private String location;
    private String shopName;
    private String fullName;
    private String phone;
    private String username;
    private String password;
    private String email;
    private String gender;
    private LocalDate birthDate;
    private Set<Role> roles = new HashSet<>();
    private List<Location> addreses = new ArrayList<>();
}

package fa.training.spring.dto.userdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormChangePassword {
    private String idFormChange;
    private String oldPassword;
    private String newPassword;

}

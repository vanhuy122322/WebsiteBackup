package fa.training.spring.dto.shopdto;

import fa.training.spring.dto.AbstractDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class ShopDetailDTO extends AbstractDTO {
    private String description;
    private String storeHouse;
    private String image;
    private String email;
    private String phone;
    private String location;
}

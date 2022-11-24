package fa.training.spring.dto.shopdto;

import fa.training.spring.dto.AbstractDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class ShopDTO extends AbstractDTO {
    private String name;
    private String slug;
    private ShopDetailDTO shopDetail;
    String userName;

}

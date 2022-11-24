package fa.training.spring.dto.productdto;

import fa.training.spring.dto.AbstractDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class OrderDTO extends AbstractDTO{
    private CheckoutDTO checkout;
    private String orderCode;
    private String status;
}

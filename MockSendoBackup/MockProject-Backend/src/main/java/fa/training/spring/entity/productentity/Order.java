package fa.training.spring.entity.productentity;

import org.springframework.data.mongodb.core.mapping.Document;

import fa.training.spring.entity.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Document("orders")
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class Order extends AbstractEntity{
    private Checkout checkout;
    private String orderCode;
    private String status;
}

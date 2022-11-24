package fa.training.spring.entity.productentity;

import fa.training.spring.entity.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class ProductInCart extends AbstractEntity {
    private String shopName;
    private String name;
    private String image;
    private String size;
    private String color;
    private double price;
    private int quantity;
    private boolean chooseStatus;
}
package fa.training.spring.entity.shopentity;

import org.springframework.data.mongodb.core.mapping.Document;

import fa.training.spring.entity.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Document(collection = "shopdetails")
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class ShopDetail extends AbstractEntity {
    private String description;
    private String storeHouse;
    private String image;
    private String email;
    private String phone;
    private String location;
}

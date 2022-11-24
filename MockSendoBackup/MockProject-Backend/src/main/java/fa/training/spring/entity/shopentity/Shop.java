package fa.training.spring.entity.shopentity;

import org.springframework.data.mongodb.core.mapping.Document;

import fa.training.spring.entity.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Document("shops")
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class Shop extends AbstractEntity {
    private String name;
    private String slug;
    private ShopDetail shopDetail;
    String userName;
}

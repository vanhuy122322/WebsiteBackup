package fa.training.spring.entity.userentity;

import org.springframework.data.mongodb.core.mapping.Document;

import fa.training.spring.entity.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Document(collection = "locations")
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class Location extends AbstractEntity{
    
    private String name;
    private String address;
    private String city;
    private String ward;
    private String phone;
    private String status;
    private String username;
}

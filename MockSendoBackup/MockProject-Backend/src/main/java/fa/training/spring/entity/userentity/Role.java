package fa.training.spring.entity.userentity;

import org.springframework.data.mongodb.core.mapping.Document;

import fa.training.spring.entity.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Document(collection = "roles")
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class Role extends AbstractEntity {

    private String name;
    public Role(String name) {
        // TODO Auto-generated constructor stub
        this.name = name;
    }
}

package fa.training.spring.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Persistable;

import lombok.Data;

@Data
public abstract class AbstractEntity implements Persistable<String> {

	@Id
	private String id;
	@CreatedDate
	private LocalDateTime createdDate;
	@LastModifiedDate
	private LocalDateTime updatedDate;
	private boolean deleteStatus;

	@Override
	public boolean isNew() {
		return this.id == null;
	}

}

package fa.training.spring.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public abstract class AbstractDTO {
	private String id;
	private LocalDateTime createdDate;
	private LocalDateTime updatedDate;
	private boolean deleteStatus;
}

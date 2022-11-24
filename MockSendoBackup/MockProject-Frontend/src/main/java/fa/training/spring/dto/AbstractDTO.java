package fa.training.spring.dto;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class AbstractDTO {
	protected String id;
	@JsonDeserialize
	@JsonSerialize
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	protected LocalDateTime createdDate;
	@JsonDeserialize
	@JsonSerialize
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	protected LocalDateTime updatedDate;
	protected boolean deleteStatus;
}
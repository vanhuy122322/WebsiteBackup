package fa.training.spring.dto;

import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDTO<T> {

	private String message;
	private String timestamp;
	private String token;
	private int totalPages;
	private HttpStatus status;
	private T data;
	private List<T> listData;
	private String uri;

	public ResponseDTO(T data, String message, HttpStatus status) {
		this.data = data;
		this.status = status;
		this.message = message;
	}

	public ResponseDTO(List<T> data, String message, HttpStatus status) {
		this.listData = data;
		this.status = status;
		this.message = message;
	}

	public ResponseDTO(T data, HttpStatus status) {
		this.data = data;
		this.status = status;
	}

	public ResponseDTO(List<T> data, HttpStatus status) {
		this.listData = data;
		this.status = status;
	}

	public ResponseDTO(String message, HttpStatus status) {
		this.message = message;
		this.status = status;
	}
}

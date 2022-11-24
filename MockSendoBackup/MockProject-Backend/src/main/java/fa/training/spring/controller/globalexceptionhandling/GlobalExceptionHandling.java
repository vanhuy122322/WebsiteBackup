package fa.training.spring.controller.globalexceptionhandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import fa.training.spring.dto.ResponseDTO;

@ControllerAdvice
public class GlobalExceptionHandling {

    @ExceptionHandler(value = { IllegalArgumentException.class })
    public ResponseEntity<ResponseDTO<String>> processIllegalArgumentException() {
        ResponseDTO<String> response = new ResponseDTO<String>();
        response.setMessage("Lỗi Khi Truyền Đối Số Không Phù Hợp!!!");
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        return ResponseEntity.ok(response);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ResponseDTO<String>> processNullPointerException() {
        ResponseDTO<String> response = new ResponseDTO<String>();
        response.setMessage("Lỗi Khi Truyền Tham Số Là Null!!!");
        response.setStatus(HttpStatus.NO_CONTENT);
        return ResponseEntity.ok(response);
    }

}

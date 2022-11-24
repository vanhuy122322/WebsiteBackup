package fa.training.spring.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestDTO<T> {
    String message;
    String typeSort;
    String input;
    String id;
    String from;
    String to;
    List<String> listColor;
    List<String> listSize;
    List<String> listOrigin;
    List<String> listMaterial;
    List<String> listTraineeId;
    List<String> listInput;
    int page;
    int size;
    T data;
    List<T> listData;

    public RequestDTO(T data) {
        this.data = data;
    }

    public RequestDTO(List<T> data) {
        this.listData = data;
    }
}

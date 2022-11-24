package fa.training.spring.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListPageDTO<T> {

	private int page;
	private int limmit;

	private int totalPage;

	private List<T> listPage;
}

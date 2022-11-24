package fa.training.spring.service.shop.serviceimpl;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import fa.training.spring.dto.RequestDTO;
import fa.training.spring.dto.ResponseDTO;
import fa.training.spring.dto.shop.ShopDTO;
import fa.training.spring.dto.user.UserDTO;
import fa.training.spring.service.shop.ExportExcelProductService;

@Service
public class ExportExcelProductServiceImpl implements ExportExcelProductService {

	private static final String API_URL = "http://localhost:8080/api/v1/";
	private final static String API_EXPORT_EXCEL_PROUCT = API_URL + "excel/products";

	@Autowired
	RestTemplate restTemplate;

	@Override
	public void exportExcelProduct() {
		RequestDTO<ShopDTO> requestDTO = new RequestDTO<ShopDTO>();
		HttpEntity<RequestDTO<ShopDTO>> request = new HttpEntity<RequestDTO<ShopDTO>>(requestDTO);
		ResponseEntity<File> response = restTemplate.exchange(API_EXPORT_EXCEL_PROUCT, HttpMethod.GET,
				null, new ParameterizedTypeReference<File>() {
				});

	}

}

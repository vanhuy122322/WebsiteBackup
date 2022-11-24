package fa.training.spring.mapper.productmapper;

import java.util.List;

import org.springframework.stereotype.Component;

import fa.training.spring.dto.productdto.ProductDetailDTO;
import fa.training.spring.entity.productentity.ProductDetail;
import fa.training.spring.mapper.AbstractMapper;

@Component
public class ProductDetailMapper extends AbstractMapper<ProductDetail, ProductDetailDTO> {
	@Override
	public ProductDetailDTO mapToDTO(ProductDetail s, Class<ProductDetailDTO> d) {
		return super.mapToDTO(s, d);
	}

	@Override
	public List<ProductDetailDTO> mapToListDTO(List<ProductDetail> sList, Class<ProductDetailDTO> d) {
		return super.mapToListDTO(sList, d);
	}

	@Override
	public ProductDetail mapToEntity(Class<ProductDetail> s, ProductDetailDTO d) {
		return super.mapToEntity(s, d);
	}

}

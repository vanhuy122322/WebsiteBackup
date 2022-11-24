package fa.training.spring.mapper.productmapper;

import java.util.List;

import org.springframework.stereotype.Component;

import fa.training.spring.dto.productdto.ProductDTO;
import fa.training.spring.entity.productentity.Product;
import fa.training.spring.mapper.AbstractMapper;

@Component
public class ProductMapper extends AbstractMapper<Product, ProductDTO> {
	@Override
	public ProductDTO mapToDTO(Product s, Class<ProductDTO> d) {
		return super.mapToDTO(s, d);
	}

	@Override
	public List<ProductDTO> mapToListDTO(List<Product> sList, Class<ProductDTO> d) {
		return super.mapToListDTO(sList, d);
	}

	@Override
	public Product mapToEntity(Class<Product> s, ProductDTO d) {
		return super.mapToEntity(s, d);
	}
	
	@Override
    public List<Product> mapToListEntity(Class<Product> s, List<ProductDTO> dList) {
        return super.mapToListEntity(s, dList);
    }


}

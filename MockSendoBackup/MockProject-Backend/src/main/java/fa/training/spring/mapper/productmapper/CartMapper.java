package fa.training.spring.mapper.productmapper;

import java.util.List;

import org.springframework.stereotype.Component;

import fa.training.spring.dto.productdto.CartDTO;
import fa.training.spring.entity.productentity.Cart;
import fa.training.spring.mapper.AbstractMapper;

@Component
public class CartMapper extends AbstractMapper<Cart, CartDTO> {
	@Override
	public CartDTO mapToDTO(Cart s, Class<CartDTO> d) {
		return super.mapToDTO(s, d);
	}

	@Override
	public List<CartDTO> mapToListDTO(List<Cart> sList, Class<CartDTO> d) {
		return super.mapToListDTO(sList, d);
	}

	@Override
	public Cart mapToEntity(Class<Cart> s, CartDTO d) {
		return super.mapToEntity(s, d);
	}

}

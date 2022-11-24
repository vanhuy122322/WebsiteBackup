package fa.training.spring.service.productservice.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fa.training.spring.Util.CustomMongoQuery;
import fa.training.spring.dto.RequestDTO;
import fa.training.spring.dto.productdto.CartDTO;
import fa.training.spring.entity.productentity.Cart;
import fa.training.spring.entity.productentity.ProductInCart;
import fa.training.spring.mapper.productmapper.CartMapper;
import fa.training.spring.repository.productrepository.CartRepository;
import fa.training.spring.service.productservice.CartService;

@Service
public class CartServiceImpl implements CartService {
	@Autowired
	CartMapper cartMapper;
	@Autowired
	CartRepository cartRepository;
	@Autowired
	CustomMongoQuery<Cart> customRepository;

	@Override
	public List<CartDTO> getAll() {
		return cartMapper.mapToListDTO(cartRepository.findAll(), CartDTO.class);
	}

	@Override
	public CartDTO getOneById(RequestDTO<CartDTO> requestDTO) {
		return cartMapper.mapToDTO(cartRepository.findById(requestDTO.getId()).orElse(null),
				CartDTO.class);
	}

	@Override
	public CartDTO add(RequestDTO<CartDTO> requestDTO) {
		Cart cart = cartMapper.mapToEntity(Cart.class, requestDTO.getData());
		ProductInCart productInCart = cart.getProductInCarts().get(0);
		Cart cartToAdd = cartRepository.findById(cart.getId()).orElse(null);
		List<ProductInCart> listProductInCart = cartToAdd.getProductInCarts();
		System.out.println(listProductInCart);
		int i = 1;
		if (listProductInCart == null) {
			return cartMapper.mapToDTO(cartRepository.save(cart), CartDTO.class);
		} else {
			for (ProductInCart pInCart : listProductInCart) {
				if (pInCart.getName().equals(productInCart.getName()) &&
						pInCart.getColor().equals(productInCart.getColor()) &&
						pInCart.getSize().equals(productInCart.getSize())) {
					pInCart.setQuantity(pInCart.getQuantity() + productInCart.getQuantity());
					i = 0;
				}
				i++;
			}
			if (i > listProductInCart.size()) {
				listProductInCart.add(productInCart);
			}
			cartToAdd.setProductInCarts(listProductInCart);
			return cartMapper.mapToDTO(cartRepository.save(cartToAdd), CartDTO.class);
		}

	}

	@Override
	public CartDTO update(RequestDTO<CartDTO> requestDTO) {
		return cartMapper.mapToDTO(
				cartRepository
						.save(cartMapper.mapToEntity(Cart.class, requestDTO.getData())),
				CartDTO.class);
	}

	@Override
	public boolean delete(RequestDTO<CartDTO> requestDTO) {
		Cart cart = cartRepository.findById(requestDTO.getId()).orElse(null);
		if (cart != null) {
			customRepository.delete(requestDTO.getId(), Cart.class);
		}

		if (cartRepository.findById(requestDTO.getId()).get().isDeleteStatus()) {
			return true;
		}
		return false;
	}

	@Override
	public CartDTO deleteProductInCart(RequestDTO<CartDTO> requestDTO) {
		Cart cart = cartRepository.findById("634d0a1e2060de6ab50534b5").orElse(null);
		cart.getProductInCarts().remove(Integer.parseInt(requestDTO.getId()));
		return cartMapper.mapToDTO(cartRepository.save(cart), CartDTO.class);
	}

	@Override
	public CartDTO getOneByUsername(RequestDTO<CartDTO> requestDTO) {
		return cartMapper.mapToDTO(cartRepository.findByUsername(requestDTO.getData().getUsername()),
				CartDTO.class);
	}
}

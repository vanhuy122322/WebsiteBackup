package fa.training.spring.service.shop;

import java.util.List;

import org.springframework.ui.Model;

import fa.training.spring.dto.RequestDTO;
import fa.training.spring.dto.product.CheckoutDTO;
import fa.training.spring.dto.product.ProductDTO;
import fa.training.spring.dto.shop.ShopDTO;

public interface ShopService {
	
	void add(RequestDTO<ShopDTO> requestDTO);

	void getOneByName(String slug, Model model);
	
	void getProductByName(String name, Model model);
	
	List<ProductDTO> getProductByShopName(String name);

	void getCheckoutByShopName(String shopName,Model model);
	
	 String updateStatusCheckout(CheckoutDTO checkoutDTO);
	 
	 void getCheckoutByCheckoutCode(String checkoutCode,Model model);
}
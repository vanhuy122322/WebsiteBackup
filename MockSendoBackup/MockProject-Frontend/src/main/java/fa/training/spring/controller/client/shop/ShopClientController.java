package fa.training.spring.controller.client.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import fa.training.spring.dto.RequestDTO;
import fa.training.spring.dto.product.CheckoutDTO;
import fa.training.spring.dto.product.ProductDTO;
import fa.training.spring.dto.shop.ShopDTO;
import fa.training.spring.service.product.CheckoutService;
import fa.training.spring.service.product.ProductService;
import fa.training.spring.service.shop.ShopService;

@Controller
@RequestMapping("/shop")
public class ShopClientController {

	@Autowired
	ShopService shopService;

	@Autowired
	ProductService productService;

	@Autowired
	CheckoutService checkoutService;

	@GetMapping("/{name}")
	public String ListingPage(@PathVariable("name") String name, Model model) {
		System.out.println(name);
		shopService.getOneByName(name, model);
		shopService.getProductByName(name, model);
		return "pages/client/shop/ShopPage";
	}

	@GetMapping("/product/{name}")
	public String ListProduct(@PathVariable("name") String name, Model model) {
		shopService.getOneByName(name, model);
		shopService.getProductByName(name, model);
		return "pages/client/shop/ListProductShop";
	}

	@GetMapping("/infomation/{name}")
	public String InfomationShop(@PathVariable("name") String name, Model model) {
		shopService.getOneByName(name, model);
		shopService.getProductByName(name, model);
		return "pages/client/shop/InfomationShop";
	}

	@PostMapping("/add")
	public String add(ShopDTO shopDTO) {
		RequestDTO<ShopDTO> requestDTO = new RequestDTO<ShopDTO>(shopDTO);
		shopService.add(requestDTO);
		return "pages/client/shop/ListProductShop";
	}

	@GetMapping("/checkout/{name}")
	public String CheckoutByShop(@PathVariable("name") String name, Model model) {
		shopService.getOneByName(name, model);
		shopService.getCheckoutByShopName(name, model);
		return "pages/client/shop/CheckoutPage";
	}

	@PostMapping("/checkout/update-status")
	@ResponseBody
	public String UpdateStatusCheckout(CheckoutDTO checkoutDTO) {
		return shopService.updateStatusCheckout(checkoutDTO);
	}

	@GetMapping("/{name}/checkout-detail")
	public String GetOneCheckout(@PathVariable String name,@RequestParam String id, Model model) {
		System.out.println(id);
		shopService.getOneByName(name, model);
		shopService.getCheckoutByCheckoutCode(id, model);
		return "pages/client/shop/CheckoutDetail";
	}

}

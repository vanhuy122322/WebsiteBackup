package fa.training.spring.service.shopservice;

import java.util.List;

import fa.training.spring.dto.RequestDTO;
import fa.training.spring.dto.productdto.CheckoutDTO;
import fa.training.spring.dto.shopdto.ShopDTO;
import fa.training.spring.dto.userdto.UserDTO;

public interface ShopService {

    ShopDTO add(RequestDTO<ShopDTO> req);
    
    ShopDTO getOneByName(RequestDTO<UserDTO> req);
    
    List<CheckoutDTO> getCheckoutByShopName(RequestDTO<ShopDTO> req);
    
    CheckoutDTO getOneByCheckOutCode(RequestDTO<CheckoutDTO> requestDTO);


}

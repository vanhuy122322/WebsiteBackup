package fa.training.spring.service.shopservice.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fa.training.spring.Util.RenderSlug;
import fa.training.spring.dto.RequestDTO;
import fa.training.spring.dto.productdto.CheckoutDTO;
import fa.training.spring.dto.productdto.ProductDTO;
import fa.training.spring.dto.shopdto.ShopDTO;
import fa.training.spring.dto.userdto.UserDTO;
import fa.training.spring.entity.shopentity.Shop;
import fa.training.spring.mapper.productmapper.CheckoutMapper;
import fa.training.spring.mapper.productmapper.ProductMapper;
import fa.training.spring.mapper.shopmapper.ShopMapper;
import fa.training.spring.mapper.usermapper.UserMapper;
import fa.training.spring.repository.productrepository.CheckoutRepository;
import fa.training.spring.repository.productrepository.ProductRepository;
import fa.training.spring.repository.shoprepository.ShopRepository;
import fa.training.spring.repository.userrepository.UserRepository;
import fa.training.spring.service.shopservice.ShopService;

@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    ShopRepository shopRepository;

    @Autowired
    ShopMapper shopMapper;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductMapper productMapper;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMapper userMapper;
    
    @Autowired
    CheckoutMapper checkoutMapper;

    @Autowired
    CheckoutRepository checkoutRepository;


   
    @Override
    public ShopDTO add(RequestDTO<ShopDTO> req) {
        Shop shop = shopMapper.mapToEntity(Shop.class, req.getData());
        shop.setSlug(RenderSlug.renderSlug(shop.getName()));
        return shopMapper.mapToDTO(shopRepository.save(shop), ShopDTO.class);
    }

    @Override
    public ShopDTO getOneByName(RequestDTO<UserDTO> req) {
        return shopMapper.mapToDTO(shopRepository.findByName(req.getData().getShopName()), ShopDTO.class);
    }

    @Override
    public List<CheckoutDTO> getCheckoutByShopName(RequestDTO<ShopDTO> req) {
        return checkoutMapper.mapToListDTO(
                checkoutRepository.findByShopName(req.getData().getName()), CheckoutDTO.class);
    }

    @Override
    public CheckoutDTO getOneByCheckOutCode(RequestDTO<CheckoutDTO> requestDTO) {
        return checkoutMapper.mapToDTO(checkoutRepository.findByCheckoutCode(requestDTO.getData().getCheckoutCode()), CheckoutDTO.class);
    }



}

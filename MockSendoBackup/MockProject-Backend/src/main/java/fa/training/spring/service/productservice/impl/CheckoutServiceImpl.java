package fa.training.spring.service.productservice.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fa.training.spring.dto.RequestDTO;
import fa.training.spring.dto.productdto.CheckoutDTO;
import fa.training.spring.dto.userdto.UserDTO;
import fa.training.spring.entity.productentity.Checkout;
import fa.training.spring.mapper.productmapper.CheckoutMapper;
import fa.training.spring.repository.productrepository.CheckoutRepository;
import fa.training.spring.service.productservice.CheckoutService;

@Service
public class CheckoutServiceImpl implements CheckoutService {

    @Autowired
    CheckoutMapper checkoutMapper;

    @Autowired
    CheckoutRepository checkoutRepository;

    @Override
    public CheckoutDTO checkout(RequestDTO<CheckoutDTO> requestDTO) {
        CheckoutDTO checkoutDTO = checkoutMapper.mapToDTO(checkoutRepository
                .save(checkoutMapper.mapToEntity(Checkout.class, requestDTO.getData())), CheckoutDTO.class);
        if (checkoutDTO != null) {
            return checkoutDTO;
        } else {
            throw new NullPointerException();
        }
    }

    @Override
    public List<CheckoutDTO> getall(RequestDTO<UserDTO> requestDTO) {
        List<CheckoutDTO> checkoutDTOs = checkoutMapper
                .mapToListDTO(checkoutRepository.findByUsername(requestDTO.getData().getUsername()), CheckoutDTO.class);
        return checkoutDTOs;
    }

    @Override
    public CheckoutDTO cancel(RequestDTO<CheckoutDTO> requestDTO) {
        CheckoutDTO checkoutDTO = checkoutMapper.mapToDTO(checkoutRepository
                .checkoutCancel(checkoutMapper.mapToEntity(Checkout.class, requestDTO.getData())), CheckoutDTO.class);
        if (checkoutDTO != null) {
            return checkoutDTO;
        } else {
            throw new NullPointerException();
        }
    }

    @Override
    public CheckoutDTO delete(RequestDTO<CheckoutDTO> requestDTO) {
        try {
            checkoutRepository
                    .deleteByCheckoutCode(requestDTO.getData().getCheckoutCode());
            return requestDTO.getData();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public CheckoutDTO updateStatus(RequestDTO<CheckoutDTO> requestDTO) {
        Checkout checkout=checkoutRepository.findByCheckoutCode(requestDTO.getData().getCheckoutCode());
        Checkout checkout2=new Checkout();
        checkout2.setCheckoutCode(checkout.getCheckoutCode());
        if(checkout.getStatus().equals("Chờ xác nhận")) {
            checkout2.setStatus("Đã xác nhận");
        }
        if(checkout.getStatus().equals("Đã xác nhận")) {
            checkout2.setStatus("Đang vận chuyển");
        }
        if(checkout.getStatus().equals("Đang vận chuyển")) {
            checkout2.setStatus("Đã thành công");
        }
        System.out.println(checkout2);
        CheckoutDTO checkoutDTO = checkoutMapper.mapToDTO(checkoutRepository
                .updateStatusCheckout(checkout2), CheckoutDTO.class);
        if (checkoutDTO != null) {
            return checkoutDTO;
        } else {
            throw new NullPointerException();
        }
    }

}
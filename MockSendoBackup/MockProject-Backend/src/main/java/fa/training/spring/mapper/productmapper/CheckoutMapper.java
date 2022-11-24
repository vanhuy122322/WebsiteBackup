package fa.training.spring.mapper.productmapper;

import java.util.List;

import org.springframework.stereotype.Component;

import fa.training.spring.dto.productdto.CheckoutDTO;
import fa.training.spring.entity.productentity.Checkout;
import fa.training.spring.mapper.AbstractMapper;

@Component
public class CheckoutMapper extends AbstractMapper<Checkout, CheckoutDTO> {

    @Override
    public CheckoutDTO mapToDTO(Checkout s, Class<CheckoutDTO> d) {
        return super.mapToDTO(s, d);
    }

    @Override
    public Checkout mapToEntity(Class<Checkout> s, CheckoutDTO d) {
        return super.mapToEntity(s, d);
    }

    @Override
    public List<CheckoutDTO> mapToListDTO(List<Checkout> sList, Class<CheckoutDTO> d) {
        return super.mapToListDTO(sList, d);
    }

    @Override
    public List<Checkout> mapToListEntity(Class<Checkout> s, List<CheckoutDTO> dList) {
        return super.mapToListEntity(s, dList);
    }

}
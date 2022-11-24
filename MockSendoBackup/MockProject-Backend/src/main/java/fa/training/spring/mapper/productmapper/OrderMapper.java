package fa.training.spring.mapper.productmapper;

import java.util.List;

import org.springframework.stereotype.Component;

import fa.training.spring.dto.productdto.OrderDTO;
import fa.training.spring.entity.productentity.Order;
import fa.training.spring.mapper.AbstractMapper;

@Component
public class OrderMapper extends AbstractMapper<Order, OrderDTO>{

    @Override
    public OrderDTO mapToDTO(Order s, Class<OrderDTO> d) {
        return super.mapToDTO(s, d);
    }

    @Override
    public Order mapToEntity(Class<Order> s, OrderDTO d) {
        return super.mapToEntity(s, d);
    }

    @Override
    public List<OrderDTO> mapToListDTO(List<Order> sList, Class<OrderDTO> d) {
        return super.mapToListDTO(sList, d);
    }

    @Override
    public List<Order> mapToListEntity(Class<Order> s, List<OrderDTO> dList) {
        return super.mapToListEntity(s, dList);
    }

}

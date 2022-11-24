package fa.training.spring.service.productservice.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fa.training.spring.dto.RequestDTO;
import fa.training.spring.dto.productdto.OrderDTO;
import fa.training.spring.entity.productentity.Order;
import fa.training.spring.mapper.productmapper.OrderMapper;
import fa.training.spring.repository.productrepository.OrderRepository;
import fa.training.spring.service.productservice.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderMapper orderMapper;

    @Override
    public OrderDTO delete(RequestDTO<OrderDTO> req) {
        System.out.println(req);
        if (req.getData().getId().isEmpty()) {
            throw new NullPointerException();
        }
        orderRepository.deleteById(req.getId());
        return req.getData();
    }

    @Override
    public OrderDTO update(RequestDTO<OrderDTO> req) {
        Order order = orderRepository.save(orderMapper.mapToEntity(Order.class, req.getData()));
        return order != null ? req.getData() : null;
    }

    @Override
    public OrderDTO add(RequestDTO<OrderDTO> req) {
        Order order = orderRepository.save(orderMapper.mapToEntity(Order.class, req.getData()));
        return order != null ? req.getData() : null;
    }

    @Override
    public OrderDTO getOneById(RequestDTO<OrderDTO> req) {
        Order order = orderRepository.findOneById(req.getId());
        return order != null ? orderMapper.mapToDTO(order, OrderDTO.class) : null;
    }

    @Override
    public OrderDTO getOneByOrderCode(RequestDTO<OrderDTO> req) {
        Order order = orderRepository.findOneById(req.getData().getOrderCode());
        return order != null ? orderMapper.mapToDTO(order, OrderDTO.class) : null;
    }

    @Override
    public List<OrderDTO> getAll() {
        return orderMapper.mapToListDTO(orderRepository.findAll(), OrderDTO.class);
    }

}

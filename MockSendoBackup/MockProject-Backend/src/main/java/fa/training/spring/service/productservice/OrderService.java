package fa.training.spring.service.productservice;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import fa.training.spring.dto.RequestDTO;
import fa.training.spring.dto.productdto.OrderDTO;

public interface OrderService {
	@CacheEvict(value = "orders", allEntries = true)
	OrderDTO delete(RequestDTO<OrderDTO> req);

	@CachePut("orderById")
	OrderDTO update(RequestDTO<OrderDTO> req);

	@Cacheable("orders")
	OrderDTO add(RequestDTO<OrderDTO> req);

	@Cacheable("orderById")
	OrderDTO getOneById(RequestDTO<OrderDTO> req);

	@Cacheable("orderByOrderCode")
	OrderDTO getOneByOrderCode(RequestDTO<OrderDTO> req);

	@Cacheable("orders")
	List<OrderDTO> getAll();
}

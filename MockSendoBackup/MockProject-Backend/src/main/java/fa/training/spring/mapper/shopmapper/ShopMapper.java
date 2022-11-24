package fa.training.spring.mapper.shopmapper;

import java.util.List;

import org.springframework.stereotype.Component;

import fa.training.spring.dto.shopdto.ShopDTO;
import fa.training.spring.entity.shopentity.Shop;
import fa.training.spring.mapper.AbstractMapper;

@Component
public class ShopMapper extends AbstractMapper<Shop, ShopDTO> {
    @Override
    public ShopDTO mapToDTO(Shop s, Class<ShopDTO> d) {
        return super.mapToDTO(s, d);
    }

    @Override
    public List<ShopDTO> mapToListDTO(List<Shop> sList, Class<ShopDTO> d) {
        return super.mapToListDTO(sList, d);
    }

    @Override
    public Shop mapToEntity(Class<Shop> s, ShopDTO d) {
        return super.mapToEntity(s, d);
    }

}

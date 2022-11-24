package fa.training.spring.mapper.shopmapper;

import java.util.List;

import org.springframework.stereotype.Component;

import fa.training.spring.dto.shopdto.ShopDetailDTO;
import fa.training.spring.entity.shopentity.ShopDetail;
import fa.training.spring.mapper.AbstractMapper;

@Component
public class ShopDetailMapper extends AbstractMapper<ShopDetail, ShopDetailDTO> {
    @Override
    public ShopDetailDTO mapToDTO(ShopDetail s, Class<ShopDetailDTO> d) {
        return super.mapToDTO(s, d);
    }

    @Override
    public List<ShopDetailDTO> mapToListDTO(List<ShopDetail> sList, Class<ShopDetailDTO> d) {
        return super.mapToListDTO(sList, d);
    }

    @Override
    public ShopDetail mapToEntity(Class<ShopDetail> s, ShopDetailDTO d) {
        return super.mapToEntity(s, d);
    }

}

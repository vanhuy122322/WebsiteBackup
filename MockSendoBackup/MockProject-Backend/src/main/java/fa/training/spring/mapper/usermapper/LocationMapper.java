package fa.training.spring.mapper.usermapper;

import java.util.List;

import org.springframework.stereotype.Component;

import fa.training.spring.dto.userdto.LocationDTO;
import fa.training.spring.entity.userentity.Location;
import fa.training.spring.mapper.AbstractMapper;


@Component
public class LocationMapper extends AbstractMapper<Location, LocationDTO>{

    @Override
    public LocationDTO mapToDTO(Location s, Class<LocationDTO> d) {
        // TODO Auto-generated method stub
        return super.mapToDTO(s, d);
    }

    @Override
    public List<LocationDTO> mapToListDTO(List<Location> sList, Class<LocationDTO> d) {
        // TODO Auto-generated method stub
        return super.mapToListDTO(sList, d);
    }

    @Override
    public Location mapToEntity(Class<Location> s, LocationDTO d) {
        // TODO Auto-generated method stub
        return super.mapToEntity(s, d);
    }

    @Override
    public List<Location> mapToListEntity(Class<Location> s, List<LocationDTO> dList) {
        // TODO Auto-generated method stub
        return super.mapToListEntity(s, dList);
    }

}

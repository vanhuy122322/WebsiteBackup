package fa.training.spring.service.userservice;

import fa.training.spring.dto.RequestDTO;
import fa.training.spring.dto.ResponseDTO;
import fa.training.spring.dto.userdto.LocationDTO;
import fa.training.spring.dto.userdto.UserDTO;

public interface LocationService {

    ResponseDTO<LocationDTO> findAllLocationByUsername(RequestDTO<UserDTO> requestDTO);
    
    ResponseDTO<LocationDTO> createLocation(RequestDTO<LocationDTO> requestDTO);
}

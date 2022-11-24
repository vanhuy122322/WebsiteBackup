package fa.training.spring.service.userservice.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import fa.training.spring.dto.RequestDTO;
import fa.training.spring.dto.ResponseDTO;
import fa.training.spring.dto.userdto.LocationDTO;
import fa.training.spring.dto.userdto.UserDTO;
import fa.training.spring.mapper.usermapper.LocationMapper;
import fa.training.spring.repository.userrepository.LocationRepository;
import fa.training.spring.service.userservice.LocationService;

@Service
public class LocationServiceImpl implements LocationService{

    @Autowired
    LocationRepository locationRepository;
    
    @Autowired
    LocationMapper locationMapper;
    
    @Override
    public ResponseDTO<LocationDTO> findAllLocationByUsername(RequestDTO<UserDTO> requestDTO) {
        List<LocationDTO> locationDTOs = locationMapper.mapToListDTO(locationRepository.findAllLocationByUsername(requestDTO.getData().getUsername()), LocationDTO.class);
        ResponseDTO<LocationDTO> responseDTO = new ResponseDTO<LocationDTO>();
        if(locationDTOs.size() > 0) {
            responseDTO.setListData(locationDTOs);
            responseDTO.setStatus(HttpStatus.OK);
        } else {
            responseDTO.setMessage("Not Found Location");
            responseDTO.setStatus(HttpStatus.NOT_FOUND);
        }
        return responseDTO;
    }

    @Override
    public ResponseDTO<LocationDTO> createLocation(RequestDTO<LocationDTO> requestDTO) {
        LocationDTO locationDTO = requestDTO.getData();
        
        return null;
    }

    
}

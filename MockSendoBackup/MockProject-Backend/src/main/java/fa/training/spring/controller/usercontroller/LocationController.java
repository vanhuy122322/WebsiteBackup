package fa.training.spring.controller.usercontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import fa.training.spring.dto.RequestDTO;
import fa.training.spring.dto.ResponseDTO;
import fa.training.spring.dto.userdto.LocationDTO;
import fa.training.spring.dto.userdto.UserDTO;
import fa.training.spring.service.userservice.LocationService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController("/api/v1/user/location")
public class LocationController {
    
    @Autowired
    LocationService locationService;
    

    @GetMapping("/")
    public ResponseDTO<LocationDTO> getLocations(RequestDTO<UserDTO> requestDTO){
        return locationService.findAllLocationByUsername(requestDTO);
    }
    
    @PostMapping("/add")
    public ResponseDTO<LocationDTO> addLocation(@RequestBody RequestDTO<LocationDTO> requestDTO){
        
        return null;
    }
}

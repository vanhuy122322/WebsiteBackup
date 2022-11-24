package fa.training.spring.service.user;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

public interface LocationService {

	void getLocations(HttpServletRequest request, Model model);
}

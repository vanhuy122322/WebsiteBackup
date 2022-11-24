package fa.training.spring.service.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface LogoutService {

	void logout(HttpServletRequest req, HttpServletResponse resp);
	
	void logoutClient(HttpServletRequest req, HttpServletResponse resp);
}

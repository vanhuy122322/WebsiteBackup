package fa.training.spring.service.login.Impl;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import fa.training.spring.service.login.LogoutService;

@Service
public class LogoutServiceImpl implements LogoutService {

	@Override
	public void logout(HttpServletRequest req, HttpServletResponse resp) {
		Cookie deleteServletCookie = new Cookie("username", "");
		deleteServletCookie.setMaxAge(0);
		resp.addCookie(deleteServletCookie);
		Cookie deleteServletCookie1 = new Cookie("token", "");
		deleteServletCookie1.setMaxAge(0);
		resp.addCookie(deleteServletCookie1);
		Cookie deleteServletCookie2 = new Cookie("token_admin", "");
		deleteServletCookie2.setMaxAge(0);
		resp.addCookie(deleteServletCookie2);
	}

	@Override
	public void logoutClient(HttpServletRequest req, HttpServletResponse resp) {
		Cookie deleteServletCookie = new Cookie("token", null);
		deleteServletCookie.setMaxAge(0);
		resp.addCookie(deleteServletCookie);
		Cookie deleteServletCookie1 = new Cookie("username", null);
		deleteServletCookie1.setMaxAge(0);
		resp.addCookie(deleteServletCookie1);
		Cookie deleteServletCookie2 = new Cookie("phone", null);
		deleteServletCookie2.setMaxAge(0);
		resp.addCookie(deleteServletCookie2);
	}

}

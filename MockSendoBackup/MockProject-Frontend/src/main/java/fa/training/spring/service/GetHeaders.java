package fa.training.spring.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
public class GetHeaders {
	@Autowired
	HttpServletRequest req;


	public HttpHeaders addHeaders() {
		try {
			String token = "";
			Cookie[] cookies = req.getCookies();
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equals("token")) {
					token = cookies[i].getValue();
				}
			}
			HttpHeaders headers = new HttpHeaders();
			headers.add("Authorization", "Bearer " + token);
			return headers;
		} catch (Exception e) {
			return null;
		}
	}
	
	public HttpHeaders addHeadersAdmin() {
		try {
			String token = "";
			Cookie[] cookies = req.getCookies();
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equals("token_admin")) {
					token = cookies[i].getValue();
				}
			}
			HttpHeaders headers = new HttpHeaders();
			headers.add("Authorization", "Bearer " + token);
			return headers;
		} catch (Exception e) {
			return null;
		}
	}
	
}

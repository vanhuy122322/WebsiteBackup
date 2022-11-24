package fa.training.spring.security.jwt;


import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import fa.training.spring.security.userprinciple.UserPrinciple;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtProvider {
	private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);
	private String jwtSecret = "sendo.clone";
	private int jwtExpiration = 86400;

	public String createToken(Authentication authentication) {
		UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();

		return Jwts.builder().setSubject(userPrinciple.getUsername()).setIssuedAt(new Date())
				.setExpiration(new Date(new Date().getTime() + jwtExpiration * 1000))
				.signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
	}

	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
			return true;
		}catch(ExpiredJwtException ex) {
			logger.error("Expired JWT token -> Message: {}", ex);
		}catch (SignatureException e) {
			logger.error("Invalid JWT signature -> Message: {}", e);
		}catch (MalformedJwtException e) {
			logger.error("Invalid format token -> Message: {}", e);
		}catch (UnsupportedJwtException e) {
			logger.error("Not support token -> Message: {}", e);
		}catch (IllegalArgumentException e) {
			logger.error("JWT claim string is empty -> Message: {}", e);
		}
		return false;
	}
	
	public String getUsernameFromToken(String token) {
		String username =  Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
		return username;
	}
}

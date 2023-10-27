package com.ethan.springsecurity.jwtdemo.util;

import com.ethan.springsecurity.jwtdemo.model.User;
import io.jsonwebtoken.*;
import java.util.Date;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenUtil {

	private final Logger log = LoggerFactory.getLogger(getClass());

	@Value("${app.jwt.secret}")
	private String SECRET_KEY;

	@Value("${app.jwt.expire.duration}")
	private long EXPIRE_DURATION;

	public String generateAccessToken(User user) {
		long now = System.currentTimeMillis();
		return Jwts.builder().setSubject(user.getId() + "," + user.getEmail())
				.addClaims(Map.of("roles", user.getUserRoles().stream().map(ur -> ur.getRole().getName()).toList()))
				.setIssuer("jwtdemo").setIssuedAt(new Date(now)).setExpiration(new Date(now + EXPIRE_DURATION))
				.signWith(SignatureAlgorithm.HS512, SECRET_KEY).compact();
	}

	public boolean validateAccessToken(String token) {
		try {
			Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token);
			return true;
		} catch (ExpiredJwtException e) {
			log.error("Expired JWT " + e.getMessage());
		} catch (MalformedJwtException e) {
			log.error("invalid JWT " + e.getMessage());
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return false;
	}

	public Claims parseClaims(String token) {
		return Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token).getBody();
	}
}

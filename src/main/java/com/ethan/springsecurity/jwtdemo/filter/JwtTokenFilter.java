package com.ethan.springsecurity.jwtdemo.filter;

import com.ethan.springsecurity.jwtdemo.util.JwtTokenUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

	private final JwtTokenUtil jwtTokenUtil;

	@Value("${app.jwt.secret}")
	private String SECRET_KEY;

	@Value("${app.jwt.expire.duration}")
	private long EXPIRE_DURATION;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
		if (authHeader == null) {
			filterChain.doFilter(request, response);
			return;
		}

		String token = authHeader.split(" ")[1];
		boolean result = jwtTokenUtil.validateAccessToken(token);
		if (!result) {
			filterChain.doFilter(request, response);
			return;
		}

		Claims claims = jwtTokenUtil.parseClaims(token);
		String[] subject = claims.getSubject().split(",");

		Long userId = Long.parseLong(subject[0]);
		String email = subject[1];
		List<String> roles = (List<String>) claims.get("roles");

		Authentication authentication = new UsernamePasswordAuthenticationToken(email, null,
				roles.stream().map(s -> new SimpleGrantedAuthority(s)).toList());

		SecurityContext context = SecurityContextHolder.createEmptyContext();
		context.setAuthentication(authentication);
		SecurityContextHolder.setContext(context);

		filterChain.doFilter(request, response);
	}
}

package com.cibertec.runner.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.cibertec.runner.dto.response.JsonResponse;
import com.cibertec.runner.service.JwtService;
import com.cibertec.runner.service.UserDetailsServiceImpl;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private final JwtService jwtService;
	private final UserDetailsServiceImpl userDetailsServiceImpl;

	public JwtAuthenticationFilter(JwtService jwtService, UserDetailsServiceImpl userDetailsServiceImpl) {
		this.jwtService = jwtService;
		this.userDetailsServiceImpl = userDetailsServiceImpl;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String authHeader = request.getHeader("Authorization");

		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}
		String jwt = authHeader.substring(7);
		try {
			String mail = jwtService.extractUsername(jwt);

			if (mail != null && jwtService.validateToken(jwt, mail)) {

				UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(mail);

				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());

				SecurityContextHolder.getContext().setAuthentication(authentication);

			}

		} catch (ExpiredJwtException e) {
			JsonResponse.writeError(response, HttpServletResponse.SC_UNAUTHORIZED, "Token expirado");
		    return;
		} catch (SignatureException | MalformedJwtException e) {
			JsonResponse.writeError(response, HttpServletResponse.SC_UNAUTHORIZED, "Token inválido o manipulado");
		    return;
		} catch (Exception e) {
			JsonResponse.writeError(response, HttpServletResponse.SC_UNAUTHORIZED, "Error al procesar el token");
		    return;
		}

		filterChain.doFilter(request, response); // Continúa con la cadena de filtros
	}
}

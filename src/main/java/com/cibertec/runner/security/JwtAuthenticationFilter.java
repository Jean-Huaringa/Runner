package com.cibertec.runner.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.cibertec.runner.service.JwtService;
import com.cibertec.runner.service.UserDetailsServiceImpl;

import io.jsonwebtoken.ExpiredJwtException;
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
		String jwt = authHeader.substring(7); // Extrae el token (sin "Bearer ")
		try {
			// extrae el mail mediante el token
			String mail = jwtService.extractUsername(jwt);

			if (mail != null && jwtService.validateToken(jwt, mail)) {

				// Obtén el UserDetails completo mediante el correo electronico
				UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(mail);

				// Establece el usuario en el contexto de seguridad
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						userDetails, // contiene los detalles del usuario como el nombre de usuario, la contraseña y
										// las autoridades .
						null, // Como estamos autentificando por rol no es necesario pasar algo, Si
								// estuviéramos usando autenticación por contraseña, aquí iría la contraseña del
								// usuario
						userDetails.getAuthorities() // (roles, permisos)
				);

				SecurityContextHolder.getContext().setAuthentication(authentication);

			}

	    } catch (ExpiredJwtException e) {
	        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	        response.getWriter().write("Token expirado");
	        return;
	    } catch (io.jsonwebtoken.security.SignatureException | io.jsonwebtoken.MalformedJwtException e) {
	        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	        response.getWriter().write("Token inválido o manipulado");
	        return;
	    } catch (Exception e) {
	        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	        response.getWriter().write("Error al procesar el token");
	        return;
	    }

		filterChain.doFilter(request, response); // Continúa con la cadena de filtros
	}
}

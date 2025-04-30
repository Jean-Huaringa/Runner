package com.cibertec.runner.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.cibertec.runner.security.JwtAuthenticationFilter;
import com.cibertec.runner.service.JwtService;
import com.cibertec.runner.service.UserDetailsServiceImpl;

@Configuration // indica que esta clase contiene definiciones de beans.
//  Spring escanea esta clase, y al ver que está marcada con @Configuration, ejecuta el método empleado() y registra el resultado como un bean.
@EnableWebSecurity // habilita la configuración de seguridad web personalizada con Spring Security.
public class SecurityConfig {

	@Autowired
	private JwtService jwtService;
	@Autowired
    private UserDetailsServiceImpl userDetailsService;

	// este metodo se ejecutara por si solo cada que envie una solicitud http
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {



		return http.csrf(csrf -> csrf.disable()) // Desactiva CSRF Permite el acceso sin autenticación a las rutas /acount/registro y /acount/create-t
				.authorizeHttpRequests(auth -> auth.requestMatchers("/api/**").permitAll() // Permite acceso sin login a estas rutas


						.anyRequest().authenticated()) // El resto requiere autenticación
				.addFilterBefore(
						new JwtAuthenticationFilter(jwtService, userDetailsService), 
						UsernamePasswordAuthenticationFilter.class) // Hace que las demas rutas de acceso requieran de autentificacion
				.build();
	}

	@Bean // Ejecuta el metodo a penas se ejecuta el programa y guarda el resultado para que lo pueda usar en otros archivos
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}

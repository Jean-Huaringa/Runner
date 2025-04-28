package com.cibertec.runner.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

	@Bean
	// se encargara de darle permisos al frond o a la url que yo coloque	
	public CorsFilter corsFilter() { 
		CorsConfiguration config = new CorsConfiguration();
		
		// Permite al Frontend con esta (url) hacer las solicitudes HTTP
		config.setAllowedOrigins(Arrays.asList("http://localhost:5173"));
		// Los permisos que le estoy dando al Frond
		config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		// Cabeceras permitidas
		config.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
		// Para permitir el envío de cookies o tokens
		config.setAllowCredentials(true);

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		// Aplica la configuración a todas las rutas
		source.registerCorsConfiguration("/**", config); 

		return new CorsFilter(source);
	}
}

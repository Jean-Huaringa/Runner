package com.cibertec.runner.service;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service // la clase se crea
//gestiona la validación y creación de tokens JWT.
public class JwtService {

	// Se define una clave secreta larga (mínimo 32 caracteres)
	@Value("${jwt.secret}")
	private String secretKey;

	// Codifica el key
	private Key getSigningKey() {
		return Keys.hmacShaKeyFor(Base64.getEncoder().encode(secretKey.getBytes()));
	}

	// Cuando se genera un toquen se implementa la fecha de caducidad entre otras
	// caracteristicas, pero la fecha u hora de cadusidad son las que mas importan
	// por que con ello podemos saber si el token aun sirve o no
	// Genera un token JWT
	public String generateToken(String mail) {
		return Jwts.builder().setSubject(mail) // el toquen se genera mediante el correo electronico
				.setIssuedAt(new Date()) // Fecha y hora en la que se generó el token
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // Expira en 1 hora
				.signWith(getSigningKey(), SignatureAlgorithm.HS256) // le dice a la biblioteca JWT que firme el token
																		// usando las dos variables que se ven
				.compact(); // Devuelve el token como una cadena (String)
	}

	// Extrae el mail del token
	public String extractUsername(String token) {
		return extractClaims(token).getSubject();
	}

	// Extrae las claims del token (información contenida en el token)
	private Claims extractClaims(String token) {
		return Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token).getBody();
	}

	// Valida si el token es válido
	public boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	// Extrae la fecha de expiración del token
	private Date extractExpiration(String token) {
		return extractClaims(token).getExpiration();
	}

	// Valida el token: si el username coincide y el token no está expirado
	public boolean validateToken(String token, String mail) {
		return (mail.equals(extractUsername(token)) && !isTokenExpired(token));
	}

}

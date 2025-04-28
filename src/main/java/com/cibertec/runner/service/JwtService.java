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

@Service // la clase se crea gestiona la validación y creación de tokens JWT.
public class JwtService {

	// Se define una clave secreta larga (mínimo 32 caracteres)
	@Value("${jwt.secret}")
	private String secretKey;

	// Codifica el key
	private Key getSigningKey() {
		return Keys.hmacShaKeyFor(Base64.getEncoder().encode(secretKey.getBytes()));
	}

	public String generateToken(String mail) {
		return Jwts.builder().setSubject(mail)
				.setIssuedAt(new Date()) 
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // el ultimo son los minutos que dura el token en minutos 
				.signWith(getSigningKey(), SignatureAlgorithm.HS256)
				.compact();
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

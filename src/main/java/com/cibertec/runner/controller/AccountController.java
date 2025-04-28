package com.cibertec.runner.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cibertec.runner.dto.request.LoginDTO;
import com.cibertec.runner.dto.request.RegisterUserDTO;
import com.cibertec.runner.dto.request.UpdatePasswordDTO;
import com.cibertec.runner.dto.request.UpdateUserDTO;
import com.cibertec.runner.dto.response.JsonResponse;
import com.cibertec.runner.model.Usuario;
import com.cibertec.runner.service.AccountService;

@RestController
@RequestMapping("/account")
public class AccountController {
	
	@Autowired
    private AccountService userService;

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> registerUser(@RequestBody RegisterUserDTO request) {
    	try {
            userService.registerUser(request);
            return ResponseEntity.ok(Map.of("mensaje", "El usuario se creo correctamente"));
		}catch (UsernameNotFoundException | BadCredentialsException | IllegalArgumentException e) {
		    return JsonResponse.buildErrorResponse(HttpStatus.UNAUTHORIZED, e.getMessage());
	    }catch (RuntimeException e) {
	        return JsonResponse.buildErrorResponse(HttpStatus.UNAUTHORIZED, e.getMessage());
		}catch (Exception e) {
	        return JsonResponse.buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error interno del servidor");
	    }
    }

    @PutMapping("/update-user")
    public ResponseEntity<Map<String, String>> updateUser(@RequestBody UpdateUserDTO request) {    
        try {
        	Usuario userDto = userService.updateUser(request);
            return ResponseEntity.ok(Map.of("mensaje", "El usuario se actualizo correctamente"));
		}catch (UsernameNotFoundException | BadCredentialsException | IllegalArgumentException  e) {
		    return JsonResponse.buildErrorResponse(HttpStatus.UNAUTHORIZED, e.getMessage());
	    } catch (Exception e) {
		    return JsonResponse.buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error interno del servidor");
	    }
    }
    
    @PostMapping("/signin")
    public ResponseEntity<Map<String, String>> signin(@RequestBody LoginDTO request) {    
        try {
			String response = userService.signin(request);
            return ResponseEntity.ok(Map.of("token", response));
		} catch (UsernameNotFoundException | BadCredentialsException | IllegalArgumentException e) {
		    return JsonResponse.buildErrorResponse(HttpStatus.UNAUTHORIZED, e.getMessage());
	    } catch (Exception e) {
		    return JsonResponse.buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error interno del servidor");
	    }
    }
    
    @PutMapping("/update-contrasenia")
    public ResponseEntity<Map<String, String>> updateClave(@RequestBody UpdatePasswordDTO request) {    
        try {
        	String token = userService.updateClave(request);
            return ResponseEntity.ok(Map.of("mensaje", token));
		} catch (UsernameNotFoundException | BadCredentialsException | IllegalArgumentException e) {
		    return JsonResponse.buildErrorResponse(HttpStatus.UNAUTHORIZED, e.getMessage());
	    } catch (Exception e) {
		    return JsonResponse.buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error interno del servidor");
	    }
    }
    
    @GetMapping("/user")
    public ResponseEntity<Usuario> getUsuarioLogueado() {    
        try {
        	Usuario token = userService.getUsuarioLogueado();
            return ResponseEntity.ok(token);
		} catch (Exception e) {
            return ResponseEntity.status(401).body(new Usuario());
		}
    }
}

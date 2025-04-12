package com.cibertec.runner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cibertec.runner.dto.LoginDTO;
import com.cibertec.runner.dto.RegisterUserDTO;
import com.cibertec.runner.dto.UpdatePasswordDTO;
import com.cibertec.runner.dto.UpdateUserDTO;
import com.cibertec.runner.model.Usuario;
import com.cibertec.runner.service.AccountService;

@RestController
@RequestMapping("/acount")
public class AccountController {
	
	@Autowired
    private AccountService userService;

    @PostMapping("/registro")
    public ResponseEntity<String> createUser(@RequestBody RegisterUserDTO usuario) {
    	try {
            userService.createUser(usuario);
            return ResponseEntity.ok("El usuario se creo correctamente");
		} catch (Exception e) {
            return ResponseEntity.status(401).body(e.getMessage());
		}
    }

    @PostMapping("/update-user")
    public ResponseEntity<Usuario> updateUser(@RequestBody UpdateUserDTO req) {    
        try {
        	Usuario userDto = userService.updateUser(req);
            return ResponseEntity.ok(userDto);
		} catch (Exception e) {
            return ResponseEntity.status(401).body(new Usuario());
		}
    }
    
    @PostMapping("/create-token")
    public ResponseEntity<String> createTokenFromAuth(@RequestBody LoginDTO request) {    
        try {
        	String token = userService.createTokenFromAuth(request);
            return ResponseEntity.ok(token);
		} catch (Exception e) {
            return ResponseEntity.status(401).body(e.getMessage());
		}
    }
    
    @PostMapping("/update-contrasenia")
    public ResponseEntity<String> updateClave(@RequestBody UpdatePasswordDTO request) {    
        try {
        	String token = userService.updateClave(request);
            return ResponseEntity.ok(token);
		} catch (Exception e) {
            return ResponseEntity.status(401).body(e.getMessage());
		}
    }
    
    @PostMapping("/user")
    public ResponseEntity<Usuario> getUsuarioLogueado() {    
        try {
        	Usuario token = userService.getUsuarioLogueado();
            return ResponseEntity.ok(token);
		} catch (Exception e) {
            return ResponseEntity.status(401).body(new Usuario());
		}
    }
}

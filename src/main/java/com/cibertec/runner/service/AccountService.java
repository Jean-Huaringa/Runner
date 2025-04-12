package com.cibertec.runner.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cibertec.runner.dto.LoginDTO;
import com.cibertec.runner.dto.RegisterUserDTO;
import com.cibertec.runner.dto.UpdatePasswordDTO;
import com.cibertec.runner.dto.UpdateUserDTO;
import com.cibertec.runner.model.Usuario;
import com.cibertec.runner.repository.IUsuarioRepository;
import com.cibertec.runner.util.ValidateText;

// log in
// sign up
// access
// account

@Service
public class AccountService {

	@Autowired
	private ValidateText vt;
	@Autowired
	private IUsuarioRepository userRepository;
	@Autowired
	private JwtService jwtService;
	@Autowired
	private PasswordEncoder passwordEncoder;

	public Usuario createUser(RegisterUserDTO req) {

		if (userRepository.findByMail(req.getMail()).isPresent()) {
			throw new IllegalArgumentException("El correo ya está registrado");
		}
		
		if (!vt.hasOnlyLettersAndSpaces(req.getNombre())) {
			throw new IllegalArgumentException ("Solo puedes ingresar letras (nombre)");
		} else if (!vt.hasValidLength(req.getNombre(), 2, 30)) {
			throw new IllegalArgumentException ("El texto debe tener entre 10 y 30 caracteres.");		}

		if (!vt.hasValidLength(req.getApellido(), 2, 30)) {
			throw new IllegalArgumentException ("El texto debe tener entre 2 y 30 caracteres.");
		} else if (!vt.hasOnlyLettersAndSpaces(req.getApellido())) {
			throw new IllegalArgumentException ("Solo puedes ingresar letras");
		}

		if (!vt.hasOnlyNumbers(req.getNmrDocumento())) {
			throw new IllegalArgumentException ("Solo puedes ingresar numeros (telefono)");
		} else if (!vt.hasValidLength(req.getNmrDocumento(), 8, 12)) {
			throw new IllegalArgumentException ("El texto debe tener entre 8 y 12 caracteres.");
		}

		if (!vt.hasOnlyNumbers(req.getTelefono())) {
			throw new IllegalArgumentException ("Solo puedes ingresar numeros (telefono)");
		} else if (!vt.hasValidLength(req.getTelefono(), 9, 12)) {
			throw new IllegalArgumentException ("El texto debe tener entre 9 y 12 caracteres.");
		}

		if (!vt.isValidMail(req.getMail())) {
			throw new IllegalArgumentException ("El gmail no tiene la estructura correcta.");
		}

		if (!vt.hasValidLength(req.getContrasenia(), 10, 60)) {
			throw new IllegalArgumentException ("El texto debe tener entre 10 y 60 caracteres.");
		} else if (!vt.hasNoneCharacterDanger(req.getContrasenia())) {
			throw new IllegalArgumentException ("Esta intento ingresar caracteres especiales que son aceptados");
		}

		Usuario user = new Usuario();
		user.setNombre(req.getNombre());
		user.setApellido(req.getApellido());
		user.setNmrDocumento(req.getNmrDocumento());
		user.setTelefono(req.getTelefono());
		user.setMail(req.getMail());
		user.setContrasenia(passwordEncoder.encode(req.getContrasenia()));
		user.setRol("user");

		return userRepository.save(user);
	}

	public Usuario updateUser(UpdateUserDTO req) {
	    String emailAutenticado = SecurityContextHolder.getContext().getAuthentication().getName();

	    Usuario user = userRepository.findByMail(emailAutenticado)
	            .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
	    
		if (!vt.hasOnlyLettersAndSpaces(req.getNombre())) {
			throw new IllegalArgumentException ("Solo puedes ingresar letras (nombre)");
		} else if (!vt.hasValidLength(req.getNombre(), 2, 30)) {
			throw new IllegalArgumentException ("El texto debe tener entre 10 y 30 caracteres.");		}

		if (!vt.hasValidLength(req.getApellido(), 2, 30)) {
			throw new IllegalArgumentException ("El texto debe tener entre 2 y 30 caracteres.");
		} else if (!vt.hasOnlyLettersAndSpaces(req.getApellido())) {
			throw new IllegalArgumentException ("Solo puedes ingresar letras");
		}

		if (!vt.hasOnlyNumbers(req.getNmrDocumento())) {
			throw new IllegalArgumentException ("Solo puedes ingresar numeros (telefono)");
		} else if (!vt.hasValidLength(req.getNmrDocumento(), 8, 12)) {
			throw new IllegalArgumentException ("El texto debe tener entre 8 y 12 caracteres.");
		}

		if (!vt.hasOnlyNumbers(req.getTelefono())) {
			throw new IllegalArgumentException ("Solo puedes ingresar numeros (telefono)");
		} else if (!vt.hasValidLength(req.getTelefono(), 9, 12)) {
			throw new IllegalArgumentException ("El texto debe tener entre 9 y 12 caracteres.");
		}


	    user.setNombre(req.getNombre());
	    user.setApellido(req.getApellido());
	    user.setNmrDocumento(req.getNmrDocumento());
	    user.setTelefono(req.getTelefono());

	    return userRepository.save(user);
	}
	
	public String createTokenFromAuth(LoginDTO request) {

		Usuario usuario = userRepository.findByMail(request.getMail()).orElse(null);

		if (usuario != null && passwordEncoder.matches(request.getContrasenia(), usuario.getContrasenia())) {
			return jwtService.generateToken(request.getMail());
		} else {
			throw new RuntimeException("Credenciales incorrectas");
		}

	}

	public String updateClave(UpdatePasswordDTO req) {
	    String emailAutenticado = SecurityContextHolder.getContext().getAuthentication().getName();

	    Usuario user = userRepository.findByMail(emailAutenticado)
	            .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

		if (!passwordEncoder.matches(req.getContraseniaActual(), user.getContrasenia())) {
			throw new BadCredentialsException("La contraseña actual es incorrecta");
		}

		if (!vt.hasValidLength(req.getNuevaContrasenia(), 10, 60)) {
			throw new IllegalArgumentException ("El texto debe tener entre 10 y 60 caracteres.");
		} else if (!vt.hasNoneCharacterDanger(req.getNuevaContrasenia())) {
			throw new IllegalArgumentException ("Esta intento ingresar caracteres especiales que son aceptados");
		}

		String newPasswordEncrypted = passwordEncoder.encode(req.getNuevaContrasenia());
		user.setContrasenia(newPasswordEncrypted);
		userRepository.save(user);
		return "Contraseña correcta";
	}

	public Usuario getUsuarioLogueado() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

	    
		if (authentication != null) {
		    Usuario user = userRepository.findByMail(authentication.getName())
		            .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
		    if(user != null) {
		    	return user;
		    }
		}
        

		throw new UsernameNotFoundException("Usuario no autenticado");
	}

}

package com.cibertec.runner.service;

import com.cibertec.runner.dto.request.LoginDTO;
import com.cibertec.runner.dto.request.RegisterUserDTO;
import com.cibertec.runner.dto.request.UpdatePasswordDTO;
import com.cibertec.runner.dto.request.UpdateUserDTO;
import com.cibertec.runner.dto.response.UserResponse;

public interface AccountService {
	
	public void registerUser(RegisterUserDTO request);
	
	public void updateUser(UpdateUserDTO request);
	
	public String signin(LoginDTO request);
	
	public void updatePassword(UpdatePasswordDTO request);
	
	public UserResponse getUsuarioLogueado(); 

}
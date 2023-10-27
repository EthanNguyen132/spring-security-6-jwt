package com.ethan.springsecurity.jwtdemo.service;

import com.ethan.springsecurity.jwtdemo.dto.RegistrationRequest;
import com.ethan.springsecurity.jwtdemo.model.User;

public interface UserService {

	public User save(RegistrationRequest reg);
}

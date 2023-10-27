package com.ethan.springsecurity.jwtdemo.controller;

import com.ethan.springsecurity.jwtdemo.dto.RegistrationRequest;
import com.ethan.springsecurity.jwtdemo.model.User;
import com.ethan.springsecurity.jwtdemo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	@PostMapping
	public User save(@RequestBody RegistrationRequest reg) {
		return userService.save(reg);
	}
}

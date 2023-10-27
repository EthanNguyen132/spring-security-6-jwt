package com.ethan.springsecurity.jwtdemo.service;

import com.ethan.springsecurity.jwtdemo.dto.RegistrationRequest;
import com.ethan.springsecurity.jwtdemo.model.Role;
import com.ethan.springsecurity.jwtdemo.model.User;
import com.ethan.springsecurity.jwtdemo.model.UserRole;
import com.ethan.springsecurity.jwtdemo.repository.RoleRepository;
import com.ethan.springsecurity.jwtdemo.repository.UserRepository;
import com.ethan.springsecurity.jwtdemo.repository.UserRoleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	public final UserRoleRepository userRoleRepository;
	public final RoleRepository roleRepository;

	@Override
	@Transactional
	public User save(RegistrationRequest reg) {
		String encryptedPassword = passwordEncoder.encode(reg.getPassword());

		User user = new User();
		user.setEmail(reg.getEmail());
		user.setPassword(encryptedPassword);

		Role role = new Role(reg.getRole());
		roleRepository.save(role);

		UserRole ur = new UserRole(user, role);
		user.addUserRole(ur);
		return userRepository.save(user);
	}
}

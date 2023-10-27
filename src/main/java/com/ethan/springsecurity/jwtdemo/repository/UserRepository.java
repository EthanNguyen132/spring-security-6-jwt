package com.ethan.springsecurity.jwtdemo.repository;

import com.ethan.springsecurity.jwtdemo.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByEmail(String username);
}

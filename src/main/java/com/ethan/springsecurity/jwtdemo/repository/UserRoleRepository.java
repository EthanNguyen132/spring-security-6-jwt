package com.ethan.springsecurity.jwtdemo.repository;

import com.ethan.springsecurity.jwtdemo.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
}

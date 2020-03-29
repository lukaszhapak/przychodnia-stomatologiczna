package com.example.przychodnia.repository;

import com.example.przychodnia.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserName(String userName);

    List<User> findAllUsersByRolesRoleName(String roleName);
}

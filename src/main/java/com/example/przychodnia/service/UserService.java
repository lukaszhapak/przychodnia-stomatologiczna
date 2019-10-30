package com.example.przychodnia.service;

import com.example.przychodnia.entity.Role;
import com.example.przychodnia.entity.User;
import com.example.przychodnia.repository.RoleRepository;
import com.example.przychodnia.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public long count() {
        return userRepository.count();
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void deleteById(String id) {
        userRepository.deleteById(Long.parseLong(id));
    }

    public void addRole(Role role, String userId) {
        User user = userRepository.findById(Long.parseLong(userId)).orElse(null);
        role = roleRepository.findById(role.getId()).orElse(null);

        if (user != null) {
            if (role != null) {
                user.getRoles().add(role);
                userRepository.save(user);
            }
        }
    }

    public User findById(String id) {
        return userRepository.findById(Long.parseLong(id)).orElse(null);
    }

    public void deleteRole(Role role, String userId) {
        User user = userRepository.findById(Long.parseLong(userId)).orElse(null);
        role = roleRepository.findById(role.getId()).orElse(null);

        if (user != null) {
            if (role != null) {
                user.getRoles().remove(role);
                userRepository.save(user);
            }
        }
    }
}

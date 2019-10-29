package com.example.przychodnia.service;

import com.example.przychodnia.entity.Role;
import com.example.przychodnia.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    final private RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public void save(Role role) {
        roleRepository.save(role);
    }

    public long count() {
        return roleRepository.count();
    }
}

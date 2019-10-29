package com.example.przychodnia.util;

import com.example.przychodnia.entity.ContactData;
import com.example.przychodnia.entity.Role;
import com.example.przychodnia.entity.User;
import com.example.przychodnia.service.ContactDataService;
import com.example.przychodnia.service.RoleService;
import com.example.przychodnia.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Optional;

@Component
public class DemoData implements CommandLineRunner {

    final private ContactDataService contactDataService;
    final private RoleService roleService;
    final private UserService userService;

    @Autowired
    public DemoData(ContactDataService contactDataService, RoleService roleService, UserService userService) {
        this.contactDataService = contactDataService;
        this.roleService = roleService;
        this.userService = userService;
    }

    @Override
    public void run(String... args) {
        if (contactDataService.count() == 0) {
            contactDataService.save(new ContactData());
        }

        if (roleService.count() == 0) {
            roleService.save(new Role("ROLE_ADMIN"));
            roleService.save(new Role("ROLE_DENTIST"));
            roleService.save(new Role("ROLE_ASSISTANT"));
            roleService.save(new Role("ROLE_RECEPTIONIST"));
            roleService.save(new Role("ROLE_PATIENT"));
        }

        if (userService.count() == 0) {
            Optional<Role> role_admin = roleService.findByRoleName("ROLE_ADMIN");

            User user = new User();
            user.setUserName("admin");
            user.setPassword("123");
            user.setRoles(new ArrayList<>());

            user.getRoles().add(role_admin.orElse(null));

            userService.save(user);
        }
    }
}
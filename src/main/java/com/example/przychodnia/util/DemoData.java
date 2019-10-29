package com.example.przychodnia.util;

import com.example.przychodnia.entity.ContactData;
import com.example.przychodnia.entity.Role;
import com.example.przychodnia.service.ContactDataService;
import com.example.przychodnia.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DemoData implements CommandLineRunner {

    final private ContactDataService contactDataService;
    final private RoleService roleService;

    @Autowired
    public DemoData(ContactDataService contactDataService, RoleService roleService) {
        this.contactDataService = contactDataService;
        this.roleService = roleService;
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
    }
}
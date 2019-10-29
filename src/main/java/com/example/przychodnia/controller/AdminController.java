package com.example.przychodnia.controller;

import com.example.przychodnia.entity.ContactData;
import com.example.przychodnia.entity.User;
import com.example.przychodnia.service.ContactDataService;
import com.example.przychodnia.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminController {

    final private ContactDataService contactDataService;
    final private UserService userService;

    @Autowired
    public AdminController(ContactDataService contactDataService, UserService userService) {
        this.contactDataService = contactDataService;
        this.userService = userService;
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        model.addAttribute(contactDataService.findAll().get(0));
        model.addAttribute(new User());
        return "admin";
    }

    @PostMapping("/admin/updateContactData/{id}")
    public String updateContactData(ContactData contactData, @PathVariable String id) {
        contactDataService.save(contactData);
        return "redirect:/admin";
    }

    @PostMapping("/admin/addUser")
    public String addUser(User user) {
        userService.save(user);
        return "redirect:/admin";
    }
}

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
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    final private ContactDataService contactDataService;
    final private UserService userService;

    @Autowired
    public AdminController(ContactDataService contactDataService, UserService userService) {
        this.contactDataService = contactDataService;
        this.userService = userService;
    }

    @GetMapping("")
    public String admin() {
        return "admin/admin";
    }

    @GetMapping("/contactData/update")
    public String updateContactDataForm(Model model) {
        model.addAttribute(contactDataService.findAll().get(0));
        return "/admin/contactData/update";
    }

    @PostMapping("/contactData/update/{id}")
    public String updateContactData(ContactData contactData, @PathVariable String id) {
        contactDataService.save(contactData);
        return "/admin/contactData/updated";
    }

    @GetMapping("/user/add")
    public String addUserForm(Model model) {
        model.addAttribute(new User());
        return "admin/user/add";
    }

    @PostMapping("/user/add")
    public String addUser(User user) {
        userService.save(user);
        return "/admin/user/added";
    }
}

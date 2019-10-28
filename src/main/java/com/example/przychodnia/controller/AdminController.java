package com.example.przychodnia.controller;

import com.example.przychodnia.service.ContactDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    final private ContactDataService contactDataService;

    @Autowired
    public AdminController(ContactDataService contactDataService) {
        this.contactDataService = contactDataService;
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        model.addAttribute(contactDataService.findAll().get(0));
        return "admin";
    }
}

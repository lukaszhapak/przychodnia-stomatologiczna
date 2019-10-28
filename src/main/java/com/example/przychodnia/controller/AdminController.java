package com.example.przychodnia.controller;

import com.example.przychodnia.entity.ContactData;
import com.example.przychodnia.service.ContactDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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

    @PostMapping("/admin/updateContactData/{id}")
    public String updateContactData(ContactData contactData, @PathVariable String id) {
        contactDataService.save(contactData);
        return "redirect:/admin";
    }
}

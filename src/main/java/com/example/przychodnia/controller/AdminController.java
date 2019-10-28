package com.example.przychodnia.controller;

import com.example.przychodnia.entity.ContactDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/admin")
    public String admin(Model model) {
        model.addAttribute(new ContactDetails());
        return "admin";
    }
}

package com.example.przychodnia.controller;

import com.example.przychodnia.entity.Message;
import com.example.przychodnia.service.ContactDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePageController {

    final private ContactDataService contactDataService;

    @Autowired
    public HomePageController(ContactDataService contactDataService) {
        this.contactDataService = contactDataService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("contactData", contactDataService.findAll().get(0));
        model.addAttribute(new Message());
        return "index";
    }
}

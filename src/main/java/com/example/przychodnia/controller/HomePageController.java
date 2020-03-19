package com.example.przychodnia.controller;

import com.example.przychodnia.entity.Message;
import com.example.przychodnia.entity.User;
import com.example.przychodnia.service.ContactDataService;
import com.example.przychodnia.service.MessageService;
import com.example.przychodnia.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomePageController {

    private final ContactDataService contactDataService;
    private final MessageService messageService;
    private final UserService userService;

    @Autowired
    public HomePageController(ContactDataService contactDataService, MessageService messageService, UserService userService) {
        this.contactDataService = contactDataService;
        this.messageService = messageService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("contactData", contactDataService.findAll().get(0));
        model.addAttribute(new Message());
        return "index";
    }

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("user", new User());
        return "user/register";
    }

    @PostMapping("/register")
    public String register(User user) {
        userService.save(user);
        return "redirect:/";
    }

    @PostMapping("/sendMessage")
    public String sendMessage(Message message) {
        messageService.save(message);
        return "redirect:/";
    }
}

package com.example.przychodnia.controller;

import com.example.przychodnia.entity.Message;
import com.example.przychodnia.service.ContactDataService;
import com.example.przychodnia.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class HomePageController {

    private final ContactDataService contactDataService;
    private final MessageService messageService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("contactData", contactDataService.findAll().get(0));
        model.addAttribute(new Message());
        return "index";
    }

    @PostMapping("/sendMessage")
    public String sendMessage(Message message) {
        messageService.save(message);
        return "redirect:/";
    }
}

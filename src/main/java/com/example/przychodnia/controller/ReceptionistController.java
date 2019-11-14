package com.example.przychodnia.controller;

import com.example.przychodnia.entity.Message;
import com.example.przychodnia.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/receptionist")
public class ReceptionistController {

    private final MessageService messageService;

    @Autowired
    public ReceptionistController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("")
    public String receptionist() {
        return "receptionist/receptionist";
    }

    @GetMapping("/messages")
    public String messages(Model model) {
        model.addAttribute("messages", messageService.findAll());
        return "receptionist/messages/messages";
    }

    @GetMapping("/message/{id}")
    public String message(Model model, @PathVariable String id) {
        Message message = messageService.findById(id);
        message.setOpened(true);
        messageService.save(message);
        model.addAttribute("message", message);
        return "receptionist/messages/message";
    }
}

package com.example.przychodnia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/receptionist")
public class ReceptionistController {

    @GetMapping("")
    public String receptionist() {
        return "receptionist/receptionist";
    }

}

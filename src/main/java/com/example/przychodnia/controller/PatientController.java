package com.example.przychodnia.controller;

import com.example.przychodnia.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/patient")
@RequiredArgsConstructor
public class PatientController {

    private final UserService userService;

    @GetMapping("")
    public String patient() {
        return "patient/patient";
    }

    @GetMapping("/visit/register")
    public String visitRegistration(Model model) {
        model.addAttribute("dentists", userService.findAllDentists());
        return "patient/visit/register";
    }
}

package com.example.przychodnia.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dentist")
@RequiredArgsConstructor
public class DentistController {

    @GetMapping("")
    public String dentist(){
        return "dentist/dentist";
    }
}

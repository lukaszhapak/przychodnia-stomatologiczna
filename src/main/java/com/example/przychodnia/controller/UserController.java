package com.example.przychodnia.controller;

import com.example.przychodnia.entity.Role;
import com.example.przychodnia.entity.User;
import com.example.przychodnia.service.RoleService;
import com.example.przychodnia.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("user", new User());
        return "user/register";
    }

    @PostMapping("/register")
    public String register(User user) {
        Role role = roleService.findByRoleName("ROLE_PATIENT").orElse(null);
        user.setRoles(Collections.singletonList(role));
        userService.save(user);
        return "redirect:/";
    }

}

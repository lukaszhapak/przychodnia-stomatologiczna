package com.example.przychodnia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.przychodnia.entity.ContactData;
import com.example.przychodnia.entity.Role;
import com.example.przychodnia.entity.User;
import com.example.przychodnia.repository.UserRepository;
import com.example.przychodnia.service.ContactDataService;
import com.example.przychodnia.service.RoleService;
import com.example.przychodnia.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final ContactDataService contactDataService;
    private final UserService userService;
    private final RoleService roleService;

    @GetMapping("")
    public String admin() {
        return "admin/admin";
    }

    @GetMapping("/contactData/update")
    public String updateContactDataForm(Model model) {
        model.addAttribute(contactDataService.findAll().get(0));
        return "admin/contactData/update";
    }

    @PostMapping("/contactData/update/{id}")
    public String updateContactData(ContactData contactData, @PathVariable String id) {
        contactDataService.save(contactData);
        return "admin/contactData/updated";
    }

    @GetMapping("/user/{id}")
    public String user(@RequestParam String id, Model model) {
        model.addAttribute("user", userService.findById(id));
        return "admin/user/add";
    }
    @GetMapping("/user/add")
    public String addUserForm(Model model) {
    	model.addAttribute(new User());
    	return "admin/user/add";
    }

    @PostMapping("/user/add")
    public String addUser(User user) {
        userService.save(user);
        return "admin/user/added";
    }

    @GetMapping("/user/list")
    public String list(Model model) {
        model.addAttribute("users", userService.findAll());
        return "admin/user/list";
    }

    @GetMapping("/user/{id}/delete")
    public String delete(@PathVariable String id) {
        userService.deleteById(id);
        return "admin/user/deleted";
    }

    @GetMapping("/user/{id}/role/grant")
    public String grantForm(Model model, @PathVariable String id) {
        model.addAttribute("roles", roleService.findAll(id));
        model.addAttribute("role", new Role());
        return "admin/user/role/grant";
    }

    @PostMapping("/user/{id}/role/grant")
    public String grant(@PathVariable String id, Role role) {
        userService.addRole(role, id);
        return "admin/user/role/granted";
    }

    @GetMapping("/user/{id}/role/delete")
    public String takeForm(Model model, @PathVariable String id) {
        model.addAttribute("roles", userService.findById(id).getRoles());
        model.addAttribute("role", new Role());
        return "admin/user/role/delete";
    }

    @PostMapping("/user/{id}/role/delete")
    public String take(@PathVariable String id, Role role) {
        userService.deleteRole(role, id);
        return "admin/user/role/deleted";
    }
}

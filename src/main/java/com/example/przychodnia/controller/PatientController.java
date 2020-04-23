package com.example.przychodnia.controller;

import com.example.przychodnia.entity.MyUserDetails;
import com.example.przychodnia.entity.User;
import com.example.przychodnia.service.RoleService;
import com.example.przychodnia.service.UserService;
import com.example.przychodnia.service.VisitsCalendarService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/patient")
public class PatientController {

    private final VisitsCalendarService visitsCalendarService;
    private final UserService userService;

    @GetMapping("")
    public String patient() {
        return "patient/patient";
    }

    @GetMapping("/visit/incoming")
    public String visits(@AuthenticationPrincipal MyUserDetails currentUser, Model model){
        Optional<User> user2 =  userService.findByUsername(currentUser.getUsername());
        String id_user = user2.get().getId().toString();
        model.addAttribute("visits",visitsCalendarService.findByPatientId(id_user));
        return "patient/visit/incoming";
    }

    @GetMapping("/visit/delete/{visitId}")
    public String takeVisit(@PathVariable String visitId) {
        visitsCalendarService.deleteVisitsById(visitId);
        return "patient/visit/delete";
    }

    @GetMapping("/visit/register")
    public String getFormViewCalendar(Model model){
        return "patient/register/form";
    }
}

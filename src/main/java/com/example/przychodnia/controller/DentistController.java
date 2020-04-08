package com.example.przychodnia.controller;

import com.example.przychodnia.entity.MyUserDetails;
import com.example.przychodnia.entity.User;
import com.example.przychodnia.service.ContactDataService;
import com.example.przychodnia.service.UserService;
import com.example.przychodnia.service.VisitsCalendarService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

@Controller
@RequestMapping("/dentist")
@RequiredArgsConstructor
public class DentistController {

    private final UserService userService;
    private final VisitsCalendarService visitsCalendarService;
    private final ContactDataService contactDataService;

    @GetMapping("")
    public String dentist(){
        return "dentist/dentist";
    }

    @GetMapping("/calendar")
    public String myCalendar(@AuthenticationPrincipal MyUserDetails currentUser, Model model){
        LocalTime open = contactDataService.findAll().get(0).getOpen();
        LocalTime close = contactDataService.findAll().get(0).getClose();
        LocalTime difrence = close.minusHours(open.getHour());
        int iterator = 30;
        Optional<User> user2 =  userService.findByUsername(currentUser.getUsername());
        String id_user = user2.get().getId().toString();
        model.addAttribute("visits",visitsCalendarService.findByDoctorId(id_user));
        model.addAttribute("now",LocalDateTime.now());
        model.addAttribute("today", LocalDateTime.now().getDayOfWeek().getValue());
        model.addAttribute("mounth",LocalDateTime.now().getMonth().getValue());
        model.addAttribute("dayMouth", LocalDateTime.now().getDayOfMonth());
        model.addAttribute("open",open);
        model.addAttribute("close",close);
        model.addAttribute("difrence",difrence);
        model.addAttribute("iterator",iterator);;
        return "dentist/calendar";
    }
}

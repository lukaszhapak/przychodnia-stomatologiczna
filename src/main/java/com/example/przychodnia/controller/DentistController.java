package com.example.przychodnia.controller;

import com.example.przychodnia.entity.MyUserDetails;
import com.example.przychodnia.entity.User;
import com.example.przychodnia.entity.VisitsCalendar;
import com.example.przychodnia.service.ContactDataService;
import com.example.przychodnia.service.UserService;
import com.example.przychodnia.service.VisitsCalendarService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
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
        int defoultTimeVisit = 30;
        Optional<User> user2 =  userService.findByUsername(currentUser.getUsername());
        String id_user = user2.get().getId().toString();
        model.addAttribute("visits",visitsCalendarService.findByDoctorId(id_user));
        model.addAttribute("today",LocalDateTime.now());
        model.addAttribute("dayOfWeek", LocalDateTime.now().getDayOfWeek().getValue());
        model.addAttribute("open",open);
        model.addAttribute("difrence",defoultTimeVisit);
        return "dentist/calendar";
    }

    @PostMapping("/calendar")
    public String calendarWeek(@AuthenticationPrincipal MyUserDetails currentUser, Model model){
        Optional<User> user =  userService.findByUsername(currentUser.getUsername());
        String id_user = user.get().getId().toString();
        model.addAttribute("visits",visitsCalendarService.findByDoctorId(id_user));
        LocalTime open = contactDataService.findAll().get(0).getOpen();
        int defoultTimeVisit = 30;
        LocalDateTime nowV = LocalDateTime.of(2020,4,20,6,0);
        model.addAttribute("today",nowV);
        model.addAttribute("dayOfWeek", nowV.getDayOfWeek().getValue());
        model.addAttribute("open",open);
        model.addAttribute("difrence",defoultTimeVisit);
        return "dentist/calendar";
    }
}

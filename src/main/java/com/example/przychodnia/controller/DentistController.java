package com.example.przychodnia.controller;

import com.example.przychodnia.entity.MyUserDetails;
import com.example.przychodnia.entity.Schedule;
import com.example.przychodnia.entity.User;
import com.example.przychodnia.repository.ScheduleRepository;
import com.example.przychodnia.service.ContactDataService;
import com.example.przychodnia.service.ScheduleService;
import com.example.przychodnia.service.UserService;
import com.example.przychodnia.service.VisitsCalendarService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/dentist")
@RequiredArgsConstructor
public class DentistController {

    private final UserService userService;
    private final VisitsCalendarService visitsCalendarService;
    private final ContactDataService contactDataService;
    private final ScheduleService scheduleService;

    private String getCurrentUser(@AuthenticationPrincipal MyUserDetails currentUser){
        Optional<User> user = userService.findByUserName(currentUser.getUsername());
        return user.get().getId().toString();
    }

    @GetMapping("")
    public String dentist() {
        return "dentist/dentist";
    }

    @GetMapping("/calendar")
    public String getCalendar(Model model) {
        String inputDateString = LocalDate.now().toString();
        model.addAttribute("inputDateString",inputDateString);
        return "dentist/calendar/form";
    }

    @PostMapping("/calendar/week")
    public String getCalendarByWeek(@AuthenticationPrincipal MyUserDetails currentUser,@RequestParam String inputDateString, Model model){
        LocalTime open = contactDataService.findAll().get(0).getOpen();
        int defoultTimeVisit = 30;
        //Optional<User> user = userService.findByUsername(currentUser.getUsername());
        //String id_user = user.get().getId().toString();
        model.addAttribute("visits", visitsCalendarService.findByDoctorId(getCurrentUser(currentUser)));
        String [] inputDateTab = inputDateString.split("-");
        LocalDateTime date = LocalDateTime.of(Integer.parseInt(inputDateTab[0]),Integer.parseInt(inputDateTab[1]),Integer.parseInt(inputDateTab[2]),0,0);
        model.addAttribute("today", date);
        model.addAttribute("dayOfWeek", date.getDayOfWeek().getValue());
        model.addAttribute("open", open);
        model.addAttribute("difrence", defoultTimeVisit);
        model.addAttribute(inputDateString);
        return "dentist/calendar/calendar";
    }

    @GetMapping("/schedule")
    public String scheduleWeek(@AuthenticationPrincipal MyUserDetails currentUser, Model model){
        model.addAttribute("schedules",scheduleService.findAllByDoctorId(getCurrentUser(currentUser)));
        return "dentist/schedule";
    }
}

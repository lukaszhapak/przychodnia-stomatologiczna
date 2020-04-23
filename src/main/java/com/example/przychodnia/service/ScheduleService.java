package com.example.przychodnia.service;

import com.example.przychodnia.entity.Schedule;
import com.example.przychodnia.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public List<Schedule> findAll() {
        return scheduleRepository.findAll();
    }

    public Iterable<Schedule> findAllByDoctorId(String id) {
        return scheduleRepository.findAllByDoctorId(Long.parseLong(id));
    }

    public Optional<Schedule> findById(String id) {
        return scheduleRepository.findById(Long.parseLong(id));
    }

    public Iterable<Schedule> findWeekByDay(String date, Long doctorId) {
        return scheduleRepository.findWeekByDate(date, doctorId);
    }

    public List<LocalDate> findFreeDayInWeek(LocalDateTime date, Long doctorId) {
        List<LocalDate> freeDayList = new ArrayList<>();
        List<Schedule> dayOnSchedule = (List<Schedule>) scheduleRepository.findWeekByDate(date.toString(), doctorId);
        int dayOfWeek = date.getDayOfWeek().getValue();
        LocalDate firstDay = LocalDate.of(date.getYear(), date.getMonth(), date.getDayOfMonth());
        firstDay = firstDay.minusDays(dayOfWeek);
        LocalDate startDay = LocalDate.of(firstDay.getYear(), firstDay.getMonth(), firstDay.getDayOfMonth());
        for (int i = 0; i < 7; i++) {
            freeDayList.add(startDay.plusDays(i + 1));
        }

        for (int i = 0; i < dayOnSchedule.size(); i++) {
            LocalDate temp = date.toLocalDate();
            for (LocalDate item : freeDayList) {
                if (dayOnSchedule.get(i).getDate().equals(item)) {
                    temp = item;
                }
            }
            freeDayList.remove(temp);
        }
        return freeDayList;
    }

    public void addSchedule(Schedule schedule) {
        scheduleRepository.save(schedule);
    }

    public void deleteScheduleById(String id) {
        scheduleRepository.deleteById(Long.parseLong(id));
    }
}

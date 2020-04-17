package com.example.przychodnia.service;

import com.example.przychodnia.entity.Schedule;
import com.example.przychodnia.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public List<Schedule> findAll(){
        return scheduleRepository.findAll();
    }

    public Iterable<Schedule> findAllByDoctorId(String id){
        return scheduleRepository.findAllByDoctorId(Long.parseLong(id));
    }
    public Optional<Schedule> findById(String id){
        return scheduleRepository.findById(Long.parseLong(id));
    }

    public void addSchedule(Schedule schedule){
        scheduleRepository.save(schedule);
    }

    public void deleteScheduleById(String id){
        scheduleRepository.deleteById(Long.parseLong(id));
    }
}

package com.example.przychodnia.repository;

import com.example.przychodnia.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    Iterable<Schedule> findAllByDoctorId(Long id);
}

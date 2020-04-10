package com.example.przychodnia.repository;

import com.example.przychodnia.entity.VisitsCalendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VisitsCalendarRepository extends JpaRepository<VisitsCalendar, Long> {

    @Query(value = "SELECT * FROM visits_calendar WHERE patient_id = :patient order by date",
            nativeQuery = true)
    Iterable<VisitsCalendar> findByIdPatient(@Param("patient") Long patient);

    @Query(value = "SELECT * FROM visits_calendar WHERE doctor_id = :doctor",
            nativeQuery = true)
    Iterable<VisitsCalendar> findByIdDoctor(@Param("doctor") Long doctor);

}

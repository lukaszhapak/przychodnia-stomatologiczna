package com.example.przychodnia.repository;

import com.example.przychodnia.entity.VisitsCalendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VisitsCalendarRepository extends JpaRepository<VisitsCalendar, Long> {

    @Query(value = "SELECT * FROM visits_calendar WHERE id_patient = :patient",
            nativeQuery = true)
    Iterable<VisitsCalendar> findByIdPatient(@Param("patient") Long patient);
}

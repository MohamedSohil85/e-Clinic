package com.example.clinic.persistence;

import com.example.clinic.entity.Appointment;
import com.example.clinic.entity.Patient;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Long> {
    List<Appointment>findAllByAppointmentTimeBetweenAndAppointmentDate(LocalTime begin, LocalTime end, LocalDate date);
    List<Appointment>findAppointmentsByAppointmentDate(LocalDate date, Pageable pageable);
    Appointment findAppointmentsByAppointmentTimeAfterAndAppointmentTimeBeforeAndAppointmentDate(LocalTime begin,LocalTime end,LocalDate date);
    List<Appointment>findAppointmentsByAppointmentTimeGreaterThanEqualAndAppointmentTimeLessThanEqualAndAppointmentDate(LocalTime start,LocalTime end,LocalDate date);
    Optional<Appointment> findAppointmentByPatient(Patient patient);
    Appointment findAppointmentByAppointmentDateAndAppointmentTime(LocalDate date,LocalTime time);
    void deleteAppointmentByPatientAndAppointmentDate(Patient patient,LocalDate date);
    Optional<Appointment> findAppointmentByAppointmentDateAndPatient(LocalDate date,Patient patient);
    List<Appointment>findAppointmentsByPatientOrderByAppointmentDate(Patient patient);

}

package com.example.clinic.persistence;

import com.example.clinic.entity.Staff;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface StaffRepository extends JpaRepository<Staff,Long> {
    Optional<Staff>findByLastNameAndFirstNameAndDateOfBirth(String lastName, String firstName, LocalDate date);
    List<Staff>findAllByJob(String job, Pageable pageable);
}

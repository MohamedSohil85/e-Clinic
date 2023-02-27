package com.example.clinic.persistence;

import com.example.clinic.entity.Clinic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClinicRepository extends JpaRepository<Clinic,Long> {
    Optional<Clinic>findByClinicName(String name);
}

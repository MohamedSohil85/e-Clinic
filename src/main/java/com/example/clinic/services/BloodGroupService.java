package com.example.clinic.services;

import com.example.clinic.entity.BloodGroup;
import com.example.clinic.entity.Patient;
import com.example.clinic.exception.ResourceNotFound;
import com.example.clinic.persistence.BloodGroupRepository;
import com.example.clinic.persistence.PatientRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BloodGroupService {

    private final PatientRepository patientRepository;
    private final BloodGroupRepository bloodGroupRepository;

    public BloodGroupService(PatientRepository patientRepository, BloodGroupRepository bloodGroupRepository) {
        this.patientRepository = patientRepository;
        this.bloodGroupRepository = bloodGroupRepository;
    }

    public ResponseEntity saveBloodTypeToPatient(BloodGroup bloodGroup,String lastName,String firstName)throws ResourceNotFound {
        Optional<Patient>optionalPatient=patientRepository.findByLastNameAndFirstName(lastName, firstName);
        Patient patient=optionalPatient.orElseThrow(()->new ResourceNotFound("Object could not found"));
        bloodGroup.setPatient(patient);
        patient.setBloodGroup(bloodGroup);
        return new ResponseEntity(bloodGroupRepository.save(bloodGroup), HttpStatus.CREATED);
    }
}

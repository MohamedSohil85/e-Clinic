package com.example.clinic.controllers;

import com.example.clinic.dto.PatientDTO;
import com.example.clinic.entity.Patient;
import com.example.clinic.persistence.AddressRepository;
import com.example.clinic.persistence.PatientRepository;
import com.example.clinic.services.PatientService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
public class PatientResource {


    private final PatientService patientService;
    private final PatientRepository patientRepository;
    private final ModelMapper modelMapper;
    private final AddressRepository addressRepository;
    private static final Logger log = LoggerFactory.getLogger(Patient.class);


    public PatientResource(PatientService patientService, PatientRepository patientRepository, ModelMapper modelMapper, AddressRepository addressRepository) {
        this.patientService = patientService;
        this.patientRepository = patientRepository;
        this.modelMapper = modelMapper;
        this.addressRepository = addressRepository;
    }
    @PostMapping(value = "/patient",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createNewPatient(@RequestBody @Valid PatientDTO patientDTO){
        return patientService.saveNewPatient(patientDTO);

    }
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/patients")
    public List<PatientDTO> showAllPatientsInfo(){
        return patientService.getAllPatients();
    }

    //delete Patient By his Name

    // find Patient by Name and Date of his Birth

    @GetMapping(value = "/GetPatient")
    public ResponseEntity findPatientByNameAndBirthday(@RequestParam("lastName")String lastName
            ,@RequestParam("firstName")String firstName
            ,@RequestParam("dateOfBirth")@DateTimeFormat(iso =DateTimeFormat.ISO.DATE,pattern = "dd/MM/yyyy") LocalDate date){
        return patientService.findPatientByNameAndBirthday(lastName, firstName, date);
    }
    @PutMapping(value = "/patients/{id}")
    public PatientDTO changePatientInfoById(@RequestBody PatientDTO patientDTO,@PathVariable("id")Long id){
        return patientService.changePatientInfo(patientDTO,id);
    }
}

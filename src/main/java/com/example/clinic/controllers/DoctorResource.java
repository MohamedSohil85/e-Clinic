package com.example.clinic.controllers;

import com.example.clinic.dto.DoctorDTO;
import com.example.clinic.services.DoctorService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class DoctorResource {

    private final DoctorService doctorService;

    public DoctorResource(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping(value = "/doctor",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createDoctor(@RequestBody DoctorDTO doctorDTO){
        return doctorService.saveNewDoctor(doctorDTO);
    }
    @GetMapping(value = "/doctors",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity loadDoctors(){
        return doctorService.loadDoctorInfo();
    }
}

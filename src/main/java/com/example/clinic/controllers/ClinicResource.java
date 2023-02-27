package com.example.clinic.controllers;

import com.example.clinic.dto.ClinicDTO;
import com.example.clinic.services.ClinicService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClinicResource {

    private ClinicService clinicService;

    public ClinicResource(ClinicService clinicService) {
        this.clinicService = clinicService;
    }

    @PostMapping(value = "/clinic",produces = MediaType.APPLICATION_JSON_VALUE)
    public ClinicDTO saveClinicInfo(@RequestBody ClinicDTO clinicDTO){
        return clinicService.saveClinic(clinicDTO);
    }
}

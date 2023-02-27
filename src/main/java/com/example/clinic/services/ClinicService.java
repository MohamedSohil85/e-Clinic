package com.example.clinic.services;

import com.example.clinic.dto.ClinicDTO;
import com.example.clinic.entity.Address;
import com.example.clinic.entity.Clinic;
import com.example.clinic.mapper.ClinicMapper;
import com.example.clinic.persistence.AddressRepository;
import com.example.clinic.persistence.ClinicRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClinicService {

    private final ClinicRepository clinicRepository;
    private final AddressRepository addressRepository;

    private final ClinicMapper clinicMapper=new ClinicMapper();

    public ClinicService(ClinicRepository clinicRepository, AddressRepository addressRepository) {
        this.clinicRepository = clinicRepository;
        this.addressRepository = addressRepository;

    }

    public ClinicDTO saveClinic(ClinicDTO clinicDTO){

        Address address=new Address();
        address.setZipcode(clinicDTO.getZipcode());
        address.setStreet(clinicDTO.getStreet());
        address.setState(clinicDTO.getState());
        address.setCountry(clinicDTO.getCountry());
        address.setCity(clinicDTO.getCity());
        addressRepository.save(address);
        Optional<Clinic> clinic_=clinicRepository.findByClinicName(clinicDTO.getClinicName());
       if (clinic_.isPresent()){
           new ResponseEntity<>(HttpStatus.FOUND);
       }
        Clinic clinic=clinicMapper.toEntity(clinicDTO);
        clinic.setAddress(address);

        clinicRepository.save(clinic);

        return clinicDTO;

    }
    //find by Clinic name
}

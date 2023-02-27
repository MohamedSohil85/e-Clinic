package com.example.clinic.services;

import com.example.clinic.dto.DoctorDTO;
import com.example.clinic.entity.Address;
import com.example.clinic.entity.Doctor;
import com.example.clinic.exception.ResourceNotFound;
import com.example.clinic.mapper.DoctorMapper;
import com.example.clinic.persistence.AddressRepository;
import com.example.clinic.persistence.DoctorRepository;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DoctorService {

    private DoctorMapper doctorMapper=new DoctorMapper();
    private DoctorRepository doctorRepository;
    private AddressRepository addressRepository;


    public DoctorService(DoctorRepository doctorRepository, AddressRepository addressRepository) {
        this.doctorRepository = doctorRepository;
        this.addressRepository = addressRepository;
    }

    public ResponseEntity saveNewDoctor(DoctorDTO doctorDTO){
        Address address=new Address();
        address.setCity(doctorDTO.getCity());
        address.setCountry(doctorDTO.getCountry());
        address.setState(doctorDTO.getState());
        address.setStreet(doctorDTO.getStreet());
        address.setZipcode(doctorDTO.getZipcode());

        addressRepository.save(address);
        Doctor doctor=doctorMapper.toEntity(doctorDTO);
        doctor.setAddress(address);

        return new ResponseEntity(doctorRepository.save(doctor), HttpStatus.CREATED);

    }
    public ResponseEntity loadDoctorInfo()throws ResourceNotFound {

        List<Doctor>doctors=doctorRepository.findAll(Sort.by("lastName"));
        if (doctors.isEmpty()){
            new ResourceNotFound("List is Empty !");
        }
        List<DoctorDTO>doctorDTOS=doctorMapper.toDto(doctors);
        return new ResponseEntity<>(doctorDTOS,HttpStatus.FOUND);
    }

}

package com.example.clinic.mapper;

import com.example.clinic.dto.DoctorDTO;
import com.example.clinic.dto.PatientDTO;
import com.example.clinic.entity.Doctor;
import com.example.clinic.entity.Patient;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class DoctorMapper implements EntityMapper<DoctorDTO, Doctor>{
    @Override
    public Doctor toEntity(DoctorDTO dto) {
        ModelMapper modelMapper=new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper.map(dto, Doctor.class);
    }

    @Override
    public DoctorDTO toDto(Doctor entity) {
        ModelMapper modelMapper=new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper.map(entity, DoctorDTO.class);    }

    @Override
    public List<Doctor> toEntity(List<DoctorDTO> dtoList) {
        return null;
    }

    @Override
    public List<DoctorDTO> toDto(List<Doctor> entityList) {

        List<DoctorDTO>doctors=new ArrayList<>();
        ModelMapper modelMapper=new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        for (Doctor doctor:entityList){
            doctors.add(modelMapper.map(doctor,DoctorDTO.class));
        }
        return doctors;
    }

    @Override
    public Set<DoctorDTO> toDto(Set<Doctor> entityList) {
        return null;
    }


}

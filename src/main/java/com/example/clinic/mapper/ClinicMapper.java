package com.example.clinic.mapper;

import com.example.clinic.dto.ClinicDTO;
import com.example.clinic.dto.DoctorDTO;
import com.example.clinic.entity.Clinic;
import com.example.clinic.entity.Doctor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import java.util.List;
import java.util.Set;

public class ClinicMapper implements EntityMapper<ClinicDTO, Clinic> {
    @Override
    public Clinic toEntity(ClinicDTO dto) {
        ModelMapper modelMapper=new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper.map(dto, Clinic.class);
    }

    @Override
    public ClinicDTO toDto(Clinic entity) {
        ModelMapper modelMapper=new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper.map(entity, ClinicDTO.class);    }

    @Override
    public List<Clinic> toEntity(List<ClinicDTO> dtoList) {
        return null;
    }

    @Override
    public List<ClinicDTO> toDto(List<Clinic> entityList) {
        return null;
    }

    @Override
    public Set<ClinicDTO> toDto(Set<Clinic> entityList) {
        return null;
    }
}

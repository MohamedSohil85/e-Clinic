package com.example.clinic.mapper;

import com.example.clinic.dto.AppointmentDTO;
import com.example.clinic.entity.Appointment;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public  class AppointmentMapper implements EntityMapper<AppointmentDTO, Appointment> {
    @Override
    public Appointment toEntity(AppointmentDTO dto) {
        ModelMapper modelMapper=new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper.map(dto,Appointment.class);
    }

    @Override
    public AppointmentDTO toDto(Appointment entity) {
        ModelMapper modelMapper=new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper.map(entity,AppointmentDTO.class);
    }

    @Override
    public List<Appointment> toEntity(List<AppointmentDTO> dtoList) {
        return null;
    }

    @Override
    public List<AppointmentDTO> toDto(List<Appointment> entityList) {
        ModelMapper modelMapper=new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        List<AppointmentDTO>appointmentDTOS=new ArrayList<>();
        for (Appointment appointment:entityList){
            appointmentDTOS.add(modelMapper.map(appointment,AppointmentDTO.class));
        }
        return appointmentDTOS;
    }

    @Override
    public Set<AppointmentDTO> toDto(Set<Appointment> entityList) {
        return null;
    }
}

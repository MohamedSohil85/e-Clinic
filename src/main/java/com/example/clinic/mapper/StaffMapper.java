package com.example.clinic.mapper;

import com.example.clinic.dto.StaffDTO;
import com.example.clinic.entity.Staff;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class StaffMapper implements EntityMapper<StaffDTO, Staff>{
    @Override
    public Staff toEntity(StaffDTO dto) {
        ModelMapper modelMapper=new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper.map(dto,Staff.class);
    }

    @Override
    public StaffDTO toDto(Staff entity) {
        ModelMapper modelMapper=new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper.map(entity,StaffDTO.class);
    }

    @Override
    public List<Staff> toEntity(List<StaffDTO> dtoList) {
        ModelMapper modelMapper=new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        List<Staff>staffList=new ArrayList<>();
        for(StaffDTO staffDTO:dtoList){
            staffList.add(modelMapper.map(staffDTO,Staff.class));
        }
        return staffList;
    }

    @Override
    public List<StaffDTO> toDto(List<Staff> entityList) {
        ModelMapper modelMapper=new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        List<StaffDTO>dtoList=new ArrayList<>();
        for (Staff staff:entityList){
            dtoList.add(modelMapper.map(staff,StaffDTO.class));
        }
        return dtoList;
    }

    @Override
    public Set<StaffDTO> toDto(Set<Staff> entityList) {
        return null;
    }
}

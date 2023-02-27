package com.example.clinic.mapper;

import com.example.clinic.dto.ReviewsDTO;
import com.example.clinic.entity.Reviews;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ReviewsMapper implements EntityMapper<ReviewsDTO, Reviews> {
    @Override
    public Reviews toEntity(ReviewsDTO dto) {
        ModelMapper modelMapper=new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper.map(dto,Reviews.class);
    }

    @Override
    public ReviewsDTO toDto(Reviews entity) {
        ModelMapper modelMapper=new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper.map(entity,ReviewsDTO.class);
    }

    @Override
    public List<Reviews> toEntity(List<ReviewsDTO> dtoList) {
        ModelMapper modelMapper=new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        List<Reviews>reviewsList=new ArrayList<>();

        dtoList.forEach(reviewsDTO -> {
            reviewsList.add(modelMapper.map(reviewsDTO,Reviews.class));
        });

        return reviewsList;
    }

    @Override
    public List<ReviewsDTO> toDto(List<Reviews> entityList) {
        ModelMapper modelMapper=new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        List<ReviewsDTO>reviewsDTOList=new ArrayList<>();
        entityList.forEach(reviews -> {
            reviewsDTOList.add(modelMapper.map(reviews,ReviewsDTO.class));
        });
        return reviewsDTOList;
    }

    @Override
    public Set<ReviewsDTO> toDto(Set<Reviews> entityList) {
        return null;
    }
}

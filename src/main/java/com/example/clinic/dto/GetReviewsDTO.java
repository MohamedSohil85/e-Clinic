package com.example.clinic.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class GetReviewsDTO {

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date dateOfReviews;
    private String description;
    private int rating;
}

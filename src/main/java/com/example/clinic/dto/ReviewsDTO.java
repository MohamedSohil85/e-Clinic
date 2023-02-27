package com.example.clinic.dto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter

public class ReviewsDTO {

    private String lastName;
    private String firstName;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDate dateOfReviews;
    private String description;
    private int rating;
    private String clinicName;
}

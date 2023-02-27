package com.example.clinic.dto;

import com.example.clinic.enumeration.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class StaffDTO {
   @NotEmpty
    private String firstName;
   @NotEmpty
    private String lastName;
   @Past
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateOfBirth;
   @NotEmpty
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String job;
    private String street;
    private String city;
    private String state;
    private String country;
    private int zipcode;
}

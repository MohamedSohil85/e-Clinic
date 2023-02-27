package com.example.clinic.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.*;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class ClinicDTO {
    @NotBlank
    @Pattern(regexp = "(\"^.*[A-Z].*[a-z].*$\")",message = "first Letter must be Capital")
    private String clinicName;
    @NotEmpty
    private String phoneNumber;
    @NotEmpty
    private String faxNumber;
    @Email(message = "Please enter valid email", regexp="^[a-zA-Z0-9._-]+@[a-zA-Z0-9-]+\\.[a-zA-Z.]{2,5}")
    @NotNull(message = "Please enter email")
    private String email;
    private String street;
    private String city;
    private String state;
    private String country;
    @NotNull
    @Positive
    private int zipcode;
    private List<OpeningTimeDTO> openingTime;
}

package com.example.clinic.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DoctorDTO {

    private String lastName;
    private String firstName;
    private String phoneNumber;
    private String Specialization;
    private String qualification;
    private String city;
    private String state;
    private String street;
    private String country;
    private int zipcode;

}

package com.example.clinic.dto;
import com.example.clinic.enumeration.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MedicalReportDTO {


    private String laboratoryName;
    private String patient_firstName;
    private String patient_lastName;
    private Gender gender;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateOfBirth;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateOfApplication;
    private String testName;
    private String result;
    private String unit;
    private String referenceValue;
    private String explanation;
    private String ApprovalBy;
}

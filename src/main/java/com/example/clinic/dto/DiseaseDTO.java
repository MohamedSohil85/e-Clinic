package com.example.clinic.dto;

import com.example.clinic.enumeration.TherapyType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class DiseaseDTO {

    private String patientLastName;
    private String patientFirstName;
    private LocalDate dateOfBirth;
    private String diseaseName;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateOfInfection;
    private String symptome;
    private String diagnose;
    private String remarks;
    private String causedBy;
    private TherapyType therapyType;
    private List<MedicineDTO> medicineDTOList;
    private String advice;

}

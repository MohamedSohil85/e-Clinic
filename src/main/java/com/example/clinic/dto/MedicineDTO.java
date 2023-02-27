package com.example.clinic.dto;

import com.example.clinic.enumeration.Condition;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class MedicineDTO {

    private String medicineName;
    private String strength;
    private String duration;
    private String dosage;
    @Enumerated(EnumType.STRING)
    private Condition condition;
}

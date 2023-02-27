package com.example.clinic.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MedicalExaminationReport implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID reportId;
    private String laboratoryName;
    @OneToOne
    private Patient patient;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateOfApplication;
    private String testName;
    private String result;
    private String unit;
    private String referenceValue;
    private String explanation;
    @OneToOne
    private Staff ApprovalBy;

}

package com.example.clinic.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

import java.time.LocalDate;
import java.util.Date;
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Disease implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long diseaseId;
    private String diseaseName;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateOfInfection;
    private String symptome;
    private String diagnose;
    private String remarks;
    private String causedBy;
    @ManyToOne
    private Patient patient;
    @OneToOne
    private Treatment treatment;
}

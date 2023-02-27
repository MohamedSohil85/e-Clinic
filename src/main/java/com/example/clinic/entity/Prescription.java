package com.example.clinic.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Prescription implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Patient patient;
    @ManyToOne
    private Clinic clinic;
    @ManyToOne
    private Doctor doctor;
    @OneToOne
    private Treatment treatment;
    @OneToMany
    private List<Medicine> medicines;
    private String advice;

}

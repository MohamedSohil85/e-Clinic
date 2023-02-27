package com.example.clinic.entity;

import com.example.clinic.enumeration.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Patient implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long patientId;
    private String firstName;
    private String lastName;
    private Gender gender;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateOfBirth;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm a")
    private LocalDateTime dateOfRegistration;
    private String nationality;
    private String phoneNumber;
    private String email;
    @OneToOne
    private Address address;
    @OneToMany(mappedBy = "patient",cascade = CascadeType.ALL)
    private List<Appointment> appointments;
    @OneToMany(mappedBy = "patient",cascade = CascadeType.ALL)
    private List<Disease>diseases;

    @OneToMany(mappedBy = "patient",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Reviews>reviewsList;
    @OneToOne(mappedBy = "patient",cascade = CascadeType.ALL)
    private BloodGroup bloodGroup;
}

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
public class Clinic implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String clinicName;
    private String phoneNumber;
    private String faxNumber;
    private String email;
    @OneToMany(mappedBy = "clinic",cascade = CascadeType.ALL)
    private List<OpeningTime>openingTime;
    @OneToMany(mappedBy = "clinic",cascade = CascadeType.ALL)
    private List<Reviews>reviews;
    @OneToOne
    Address address;
}

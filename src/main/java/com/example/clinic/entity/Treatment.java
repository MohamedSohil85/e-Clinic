package com.example.clinic.entity;

import com.example.clinic.enumeration.TherapyType;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Treatment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private TherapyType therapyType;
    @OneToMany
    private List<Medicine> medicines;
    private String advice;

}

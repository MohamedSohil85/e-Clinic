package com.example.clinic.entity;

import com.example.clinic.enumeration.Condition;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Medicine implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String medicineName;
    private String strength;
    private String duration;
    private String dosage;
    @Enumerated(EnumType.STRING)
    private Condition condition;
}

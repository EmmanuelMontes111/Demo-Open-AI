package com.example.demo.infrastructure.adapter.entity;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder(toBuilder = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "patient")
public class PatientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String career;
    private Long documentNumber;
    private int age;
    private String diseases;
    private int systolicPressure;
    private int diastolicPressure;
    private int sugarLevel;
    private int heartRate;
    private double oxygenation;
    private String eventDescription;
    private Long creationDate;
    private Long modificationDate;
}

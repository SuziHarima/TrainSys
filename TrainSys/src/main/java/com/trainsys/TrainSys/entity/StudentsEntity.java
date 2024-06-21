package com.trainsys.TrainSys.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "students")
@Data
public class StudentsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDate dateBirth;

    @Column(nullable = false, unique = true)
    private String cpf;

    @Column(nullable = false)
    private String contact;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;


    private String city;
    private String neighborhood;
    private String number;
    private String street;
    private String state;
    private String cep;
}

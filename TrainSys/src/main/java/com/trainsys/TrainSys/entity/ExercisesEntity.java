package com.trainsys.TrainSys.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table (name = "exercises")
public class ExercisesEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn (name = "user_id")
    private UserEntity user;
}

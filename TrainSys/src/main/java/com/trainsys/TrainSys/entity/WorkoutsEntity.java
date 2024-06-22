package com.trainsys.TrainSys.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "workouts")
@Data
public class WorkoutsEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private Integer repetitions;

    @Column(nullable = false)
    private double weight;

    @Column(nullable = false)
    private Integer breakTime;

    private WeekDay day;
    private String observations;

    @Column(nullable = false)
    private Integer time;

    @JoinColumn (name = "students_id")
    @ManyToOne
    private StudentsEntity student;

    @JoinColumn (name = "exercises_id")
    @ManyToOne
    private ExercisesEntity exercise;

}

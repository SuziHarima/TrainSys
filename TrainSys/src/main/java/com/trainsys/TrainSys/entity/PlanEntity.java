package com.trainsys.TrainSys.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "plan")
public class PlanEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    String name;
}

package com.trainsys.TrainSys.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "plan")
@Data
public class PlanEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    String name;
}

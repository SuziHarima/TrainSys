package com.trainsys.TrainSys.entity;

import com.trainsys.TrainSys.controller.request.LoginRequest;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
@Data
@Table (name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private Integer id;

    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String dateBirth;

    @Column(unique = true)
    private String cpf;

    @Column(nullable = false)
    private String password;

    @OneToOne
    @JoinColumn (name = "plan_id")
    private PlanEntity plan;


    public boolean validatePassword(LoginRequest loginRequest, BCryptPasswordEncoder bCryptEncoder) {
        return bCryptEncoder.matches(loginRequest.password(), this.password);
    }
}

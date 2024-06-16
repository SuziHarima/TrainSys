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
    Integer id;

    String name;

    @Column(unique = true, nullable = false)
    String email;

    @Column(nullable = false)
    String dateBirth;

    @Column(unique = true)
    String cpf;

    @Column(nullable = false)
    String password;


    public boolean validatePassword(LoginRequest loginRequest, BCryptPasswordEncoder bCryptEncoder) {
        return bCryptEncoder.matches(loginRequest.password(), this.password);
    }
}

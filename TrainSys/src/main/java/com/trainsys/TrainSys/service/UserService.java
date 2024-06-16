package com.trainsys.TrainSys.service;


import com.trainsys.TrainSys.controller.request.NewUserRequest;
import com.trainsys.TrainSys.entity.UserEntity;
import com.trainsys.TrainSys.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void userRegistration(@RequestBody NewUserRequest newUserRequest) {
        boolean usuarioExsite = userRepository.findByEmail(newUserRequest.email()).isPresent();
        if (usuarioExsite) {
            throw new RuntimeException("Login já cadastrado");
        }

        UserEntity user = new UserEntity();
        user.setName(newUserRequest.name());
        user.setEmail(newUserRequest.email());
        user.setDateBirth(newUserRequest.dateBirth());
        user.setCpf(newUserRequest.cpf());
        user.setPassword(bCryptPasswordEncoder.encode(newUserRequest.password()));

        userRepository.save(user);
    }
}

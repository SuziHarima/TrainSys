package com.trainsys.TrainSys.controller;


import com.trainsys.TrainSys.controller.request.NewUserRequest;
import com.trainsys.TrainSys.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/cadastro")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<String> saveUser(@Validated @RequestBody NewUserRequest newUserRequest){
        userService.userRegistration(newUserRequest);
        log.info("POST /users -> 201 CREATED");
        return ResponseEntity.ok("Cadastro realizado com sucesso");
    }
}

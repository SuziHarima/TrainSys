package com.trainsys.TrainSys.controller;


import com.trainsys.TrainSys.controller.request.LoginRequest;
import com.trainsys.TrainSys.controller.response.LoginResponse;
import com.trainsys.TrainSys.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TokenController {

    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> generateToken(@RequestBody LoginRequest loginRequest){
        LoginResponse response = tokenService.generateToken(loginRequest);
        return ResponseEntity.ok(response);
    }
}

package com.trainsys.TrainSys.service;


import com.trainsys.TrainSys.controller.request.LoginRequest;
import com.trainsys.TrainSys.controller.response.LoginResponse;
import com.trainsys.TrainSys.entity.UserEntity;
import com.trainsys.TrainSys.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.Instant;

@Service
@Slf4j
@RequiredArgsConstructor
public class TokenService {
    private final BCryptPasswordEncoder bCryptEncoder;
    private final JwtEncoder jwtEncoder;
    private final JwtDecoder jwtDecoder;
    private final UserRepository userRepository;

    private static long EXPIRATION_TIME = 86400L;


    public LoginResponse generateToken(@RequestBody LoginRequest loginRequest){
        UserEntity user = userRepository.findByEmail(loginRequest.email()).orElseThrow(
                        ()->{
                            log.error("E-mail não encontrado");
                            return new RuntimeException("E-mail não encontrado");
                        });
        if(!user.validatePassword(loginRequest, bCryptEncoder)){
            log.error("Senha inválida");
            throw new RuntimeException("Senha inválida");
        }
        Instant now = Instant.now();
        String scope = user.getRole().toString();
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("trainsys")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(EXPIRATION_TIME))
                .subject(user.getId().toString())
                .claim("scope", scope)
                .build();
        var JWTValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
        return new LoginResponse(JWTValue, user.getName(), EXPIRATION_TIME);
    }

    public String fieldSearch(String token, String claim) {
        return jwtDecoder
                .decode(token)
                .getClaims()
                .get(claim)
                .toString();
    }

}

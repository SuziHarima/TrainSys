package com.trainsys.TrainSys.controller.response;

import com.fasterxml.jackson.annotation.JsonFormat;

public record LoginResponse(String JWTValue,
                            String name,
                            @JsonFormat (pattern = "HH:mm")
                            long expirationTime) {
}

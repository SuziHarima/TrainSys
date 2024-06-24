package com.trainsys.TrainSys.controller.request;

public record NewUserRequest(String name,
                             String email,
                             String dateBirth,
                             String cpf,
                             String password,
                             Integer plan) {
}

package com.trainsys.TrainSys.controller.request;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record NewStudentRequest(String name,
                                String email,

                                @JsonFormat(pattern = "yyyy/MM/dd")
                                LocalDate dateBirth,
                                String cpf,
                                String contact,
                                String city,
                                String neighborhood,
                                String number,
                                String street,
                                String state,
                                String cep) {
}

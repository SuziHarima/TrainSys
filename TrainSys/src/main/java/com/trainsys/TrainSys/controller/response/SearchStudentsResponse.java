package com.trainsys.TrainSys.controller.response;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record SearchStudentsResponse(Integer id,
                                     String name,
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

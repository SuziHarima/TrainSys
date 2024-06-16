package com.trainsys.TrainSys.controller.request;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record LoginRequest(String email,
                           String password,
                           @JsonFormat(pattern = "yyy/MM/dd")
                           LocalDate localDate,

                           @JsonFormat(pattern = "yyy/MM/dd HH:mm:ss")
                           LocalDateTime localDateTime) {
}

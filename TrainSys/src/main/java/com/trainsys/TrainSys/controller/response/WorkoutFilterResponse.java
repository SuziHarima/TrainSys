package com.trainsys.TrainSys.controller.response;

import com.trainsys.TrainSys.entity.WeekDay;

public record WorkoutFilterResponse(String exercise,
                                    Integer repetitions,
                                    double weight,
                                    Integer breakTime,
                                    String observations,
                                    Integer time) {
}

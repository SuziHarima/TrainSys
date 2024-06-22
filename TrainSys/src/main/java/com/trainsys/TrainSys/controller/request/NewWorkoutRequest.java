package com.trainsys.TrainSys.controller.request;

import com.trainsys.TrainSys.entity.ExercisesEntity;
import com.trainsys.TrainSys.entity.StudentsEntity;
import com.trainsys.TrainSys.entity.WeekDay;

public record NewWorkoutRequest(Integer student,
                                Integer exercise,
                                Integer repetitions,
                                double weight,
                                Integer breakTime,
                                WeekDay day,
                                String observations,
                                Integer time) {
}

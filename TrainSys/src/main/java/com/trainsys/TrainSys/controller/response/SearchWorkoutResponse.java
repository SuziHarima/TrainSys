package com.trainsys.TrainSys.controller.response;

import com.trainsys.TrainSys.entity.WeekDay;

import java.util.List;
import java.util.Map;

public record SearchWorkoutResponse(Integer student_id,
                                    String student_name,
                                    Map<String, List<WorkoutFilterResponse>> workouts) {
}

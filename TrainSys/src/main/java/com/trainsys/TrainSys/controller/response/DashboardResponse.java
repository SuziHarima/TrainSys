package com.trainsys.TrainSys.controller.response;

import com.trainsys.TrainSys.entity.PlanEntity;

public record DashboardResponse(Integer registeredStudents,
                                Integer registeredExercises,
                                String currentUserPlan,
                                Integer remainingStudents
) {
}

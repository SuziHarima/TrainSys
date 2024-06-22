package com.trainsys.TrainSys.service;


import com.trainsys.TrainSys.controller.request.NewWorkoutRequest;
import com.trainsys.TrainSys.controller.response.WorkoutResponse;
import com.trainsys.TrainSys.entity.ExercisesEntity;
import com.trainsys.TrainSys.entity.StudentsEntity;
import com.trainsys.TrainSys.entity.WorkoutsEntity;
import com.trainsys.TrainSys.repository.ExercisesRepository;
import com.trainsys.TrainSys.repository.StudentsRepository;
import com.trainsys.TrainSys.repository.WorkoutsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class WorkoutService {

    private final WorkoutsRepository workoutsRepository;
    private final TokenService tokenService;
    private final StudentsRepository studentsRepository;
    private final ExercisesRepository exercisesRepository;


    public WorkoutResponse workoutsRegistration(NewWorkoutRequest newWorkoutRequest, String token) {
        //valida papel de usuario
        String userRole =  tokenService.fieldSearch(token, "scope");
        if (!Objects.equals(userRole, "ADM")){
            throw new RuntimeException("Usuario não tem acesso a essa funcionalidade");
        }

        WorkoutsEntity workout = new WorkoutsEntity();
        workout.setStudent(studentsRepository.findById(newWorkoutRequest.student()).orElseThrow(() -> new RuntimeException("Plano inválido ou inexistente")));
        workout.setExercise(exercisesRepository.findById(newWorkoutRequest.exercise()).orElseThrow(() -> new RuntimeException("Plano inválido ou inexistente")));
        workout.setRepetitions(newWorkoutRequest.repetitions());
        workout.setWeight(newWorkoutRequest.weight());
        workout.setBreakTime(newWorkoutRequest.breakTime());
        workout.setDay(newWorkoutRequest.day());
        workout.setObservations(newWorkoutRequest.observations());
        workout.setTime(newWorkoutRequest.time());


        workoutsRepository.save(workout);

        return new WorkoutResponse();
    }
}

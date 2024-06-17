package com.trainsys.TrainSys.service;


import com.trainsys.TrainSys.controller.request.NewExerciseRequest;
import com.trainsys.TrainSys.controller.response.ExerciseResponse;
import com.trainsys.TrainSys.entity.ExercisesEntity;
import com.trainsys.TrainSys.repository.ExercisesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ExercisesService {

    private final ExercisesRepository exercisesRepository;
    private final TokenService tokenService;

    public ExerciseResponse exerciseRegistration(NewExerciseRequest newExerciseRequest, String token) {
        //valida papel de usuario
        String userRole =  tokenService.fieldSearch(token, "scope");
        if (!Objects.equals(userRole, "ADM")){
            throw new RuntimeException("Usuario não tem acesso a essa funcionalidade");
        }

        ExercisesEntity exercisesEntity = new ExercisesEntity();
        exercisesEntity.setDescription(newExerciseRequest.description());

        ExercisesEntity registeredExercise = exercisesRepository.save(exercisesEntity);

        return new ExerciseResponse();
    }

}

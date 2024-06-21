package com.trainsys.TrainSys.service;


import com.trainsys.TrainSys.controller.request.NewExerciseRequest;
import com.trainsys.TrainSys.controller.response.ExerciseResponse;
import com.trainsys.TrainSys.controller.response.SearchExerciseResponse;
import com.trainsys.TrainSys.entity.ExercisesEntity;
import com.trainsys.TrainSys.entity.UserEntity;
import com.trainsys.TrainSys.repository.ExercisesRepository;
import com.trainsys.TrainSys.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ExercisesService {

    private final ExercisesRepository exercisesRepository;
    private final UserRepository userRepository;
    private final TokenService tokenService;

    public ExerciseResponse exerciseRegistration(NewExerciseRequest newExerciseRequest, String token) {
        //valida papel de usuario
        String userRole =  tokenService.fieldSearch(token, "scope");
        if (!Objects.equals(userRole, "ADM")){
            throw new RuntimeException("Usuario não tem acesso a essa funcionalidade");
        }
        String userId = tokenService.fieldSearch(token, "sub");
        UserEntity user = userRepository.findById(Integer.parseInt(userId)).orElseThrow(
                ()->{
                    return new RuntimeException("Usuário não encontrado");
                });

        ExercisesEntity exercisesEntity = new ExercisesEntity();
        exercisesEntity.setDescription(newExerciseRequest.description());
        exercisesEntity.setUser(user);

        exercisesRepository.save(exercisesEntity);

        return new ExerciseResponse();
    }

    public List<SearchExerciseResponse> searchAll(String token) {
        String userRole = tokenService.fieldSearch(token, "scope");
        if (!Objects.equals(userRole, "ADM")) {
            throw new RuntimeException("Usuario não tem acesso a essa funcionalidade");
        }
        List<ExercisesEntity> exercises =  exercisesRepository.findAll();

        return exercises.stream().map(e -> new SearchExerciseResponse(e.getId(), e.getDescription())).toList();
    }

    public void removeById(Integer id, String token) {
        String userRole = tokenService.fieldSearch(token, "scope");
        if (!Objects.equals(userRole, "ADM")) {
            throw new RuntimeException("Usuario não tem acesso a essa funcionalidade");
        }
        exercisesRepository.deleteById(id);
    }

}

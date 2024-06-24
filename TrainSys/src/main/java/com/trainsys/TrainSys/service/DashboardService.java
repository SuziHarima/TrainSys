package com.trainsys.TrainSys.service;


import com.trainsys.TrainSys.controller.response.DashboardResponse;
import com.trainsys.TrainSys.entity.ExercisesEntity;
import com.trainsys.TrainSys.entity.StudentsEntity;
import com.trainsys.TrainSys.entity.UserEntity;
import com.trainsys.TrainSys.repository.ExercisesRepository;
import com.trainsys.TrainSys.repository.PlanRepository;
import com.trainsys.TrainSys.repository.StudentsRepository;
import com.trainsys.TrainSys.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class DashboardService {
    private final TokenService tokenService;
    private final UserRepository userRepository;
    private final StudentsRepository studentsRepository;
    private final ExercisesRepository exercisesRepository;
    private final PlanRepository planRepository;

//    registered_students: quantidade de estudantes cadastrados pelo usuário
//    registered_exercises: quantidade de exercícios cadastrados pelo usuário
//    current_user_plan: plano atual do usuário
//    remaining_estudants: quantidade de cadastros restantes de estudantes

    public DashboardResponse searchByUser(String token) {
        String userRole = tokenService.fieldSearch(token, "scope");
        if (!Objects.equals(userRole, "ADM")) {
            throw new RuntimeException("Usuario não tem acesso a essa funcionalidade");
        }

        //busca id do usuário que está logado pela chave
        Integer userId = Integer.valueOf(tokenService.fieldSearch(token, "sub"));
        //retorna uma entidade usuário pelo id
        UserEntity user = userRepository.findById(userId).orElseThrow(() ->
                new RuntimeException("Usuário não encontrado"));


        //lista todas as entidades de estudantes
        List<StudentsEntity> studentsByUser =  studentsRepository.findAll();

        //para cada estudante que tiver user_id == user adiciona mais um na contagem de estudantes registrados
        Integer studentCount = 0;
        for(StudentsEntity s : studentsByUser){
            if(s.getUser() == user){
                studentCount++;
            }
        }

        //fazer o mesmo com exercícios
        List<ExercisesEntity> exercisesByUser =  exercisesRepository.findAll();

        //para cada estudante que tiver user_id == user adiciona mais um na contagem de estudantes registrados
        Integer exerciseCount = 0;
        for(ExercisesEntity e : exercisesByUser){
            if(e.getUser() == user){
                exerciseCount++;
            }
        }

        //estudantes restantes no plano
        //pegar quantidade limite menos quantidade cadastrada

        Integer remainingStudents = user.getPlan().getLimitStudent() - user.getPlan().getCurrentQuantity();

        return new DashboardResponse(
                studentCount,
                exerciseCount,
                user.getPlan().getName(),
                remainingStudents);
    }


}

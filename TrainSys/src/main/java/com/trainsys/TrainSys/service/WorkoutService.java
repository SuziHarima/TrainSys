package com.trainsys.TrainSys.service;


import com.trainsys.TrainSys.controller.request.NewWorkoutRequest;
import com.trainsys.TrainSys.controller.response.SearchWorkoutResponse;
import com.trainsys.TrainSys.controller.response.WorkoutFilterResponse;
import com.trainsys.TrainSys.controller.response.WorkoutResponse;
import com.trainsys.TrainSys.entity.StudentsEntity;
import com.trainsys.TrainSys.entity.WeekDay;
import com.trainsys.TrainSys.entity.WorkoutsEntity;
import com.trainsys.TrainSys.repository.ExercisesRepository;
import com.trainsys.TrainSys.repository.StudentsRepository;
import com.trainsys.TrainSys.repository.WorkoutsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

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
        workout.setStudent(studentsRepository.findById(newWorkoutRequest.student()).orElseThrow(() -> new RuntimeException("Estudante inválido ou inexistente")));
        workout.setExercise(exercisesRepository.findById(newWorkoutRequest.exercise()).orElseThrow(() -> new RuntimeException("Exercicio inválido ou inexistente")));
        workout.setRepetitions(newWorkoutRequest.repetitions());
        workout.setWeight(newWorkoutRequest.weight());
        workout.setBreakTime(newWorkoutRequest.breakTime());
        workout.setDay(newWorkoutRequest.day());
        workout.setObservations(newWorkoutRequest.observations());
        workout.setTime(newWorkoutRequest.time());


        workoutsRepository.save(workout);

        return new WorkoutResponse();
    }

    public SearchWorkoutResponse searchAllByStudentId(String token, Integer studentId) {
        String userRole = tokenService.fieldSearch(token, "scope");
        if (!Objects.equals(userRole, "ADM")) {
            throw new RuntimeException("Usuario não tem acesso a essa funcionalidade");
        }

        List<WorkoutsEntity> workouts =  workoutsRepository.findAllByStudentId(studentId);
        Map<String, List<WorkoutFilterResponse>> filteredWorkout = new HashMap<>();


        for(WeekDay day : WeekDay.values()){
            // adicionar no map o weekday "SEGUNDA" = new List()
            String key = day.name();
            List<WorkoutFilterResponse> value = new ArrayList<>();
            filteredWorkout.put(key, value);

            // pra cada workout da lista workouts, se for desse weekday, adiciona na lista atual
            for(WorkoutsEntity w : workouts){
                if(w.getDay() == day){
                    value.add(
                            new WorkoutFilterResponse(
                                    w.getExercise().getDescription(),
                                    w.getRepetitions(),
                                    w.getWeight(),
                                    w.getBreakTime(),
                                    w.getObservations(),
                                    w.getTime())
                    );
                }
            }
        }

        StudentsEntity student = studentsRepository.findById(studentId).orElseThrow(()
                -> new RuntimeException("Estudante inválido ou inexistente"));



        return new SearchWorkoutResponse(
                studentId,
                student.getName(),
                filteredWorkout);
    }

}

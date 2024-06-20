package com.trainsys.TrainSys.controller;


import com.trainsys.TrainSys.controller.request.NewExerciseRequest;
import com.trainsys.TrainSys.controller.response.ExerciseResponse;
import com.trainsys.TrainSys.entity.ExercisesEntity;
import com.trainsys.TrainSys.service.ExercisesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("exercises")
@RequiredArgsConstructor
@Slf4j
public class ExerciseController {

    private final ExercisesService exercisesService;

    @PostMapping
    public ResponseEntity<ExerciseResponse> saveExercise(@RequestHeader(name = "Authorization") String token, @RequestBody NewExerciseRequest newExerciseRequest){
        log.info("POST /users -> 201 CREATED");
        return ResponseEntity.ok(exercisesService.exerciseRegistration(newExerciseRequest,token.substring(7)));
    }

    @GetMapping
    public ResponseEntity<List<ExercisesEntity>> listAll(@RequestHeader(name = "Authorization") String token){
        log.info("GET /exercises -> OK");
        return ResponseEntity.ok(exercisesService.serachAll(token.substring(7)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeExerciseById(@PathVariable Integer id){
        exercisesService.removeById(id, "");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

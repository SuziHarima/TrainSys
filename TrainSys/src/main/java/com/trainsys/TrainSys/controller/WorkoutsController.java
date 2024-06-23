package com.trainsys.TrainSys.controller;


import com.trainsys.TrainSys.controller.request.NewWorkoutRequest;
import com.trainsys.TrainSys.controller.response.SearchWorkoutResponse;
import com.trainsys.TrainSys.controller.response.WorkoutResponse;
import com.trainsys.TrainSys.service.WorkoutService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("workouts")
@RequiredArgsConstructor
@Slf4j
public class WorkoutsController {

    private final WorkoutService workoutService;

    @PostMapping
    public ResponseEntity<WorkoutResponse> saveWorkout(@RequestHeader(name = "Authorization") String token, @RequestBody NewWorkoutRequest newWorkoutRequest){
        log.info("POST /workout -> 201 CREATED");
        return ResponseEntity.ok(workoutService.workoutsRegistration(newWorkoutRequest,token.substring(7)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SearchWorkoutResponse> listAllByStudentId(@RequestHeader(name = "Authorization") String token, @PathVariable Integer id){
        log.info("GET /workout -> OK");
        return ResponseEntity.ok(workoutService.searchAllByStudentId(token.substring(7), id));
    }
}

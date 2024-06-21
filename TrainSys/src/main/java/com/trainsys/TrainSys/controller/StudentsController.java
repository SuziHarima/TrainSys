package com.trainsys.TrainSys.controller;


import com.trainsys.TrainSys.controller.request.NewStudentRequest;
import com.trainsys.TrainSys.controller.response.StudentResponse;
import com.trainsys.TrainSys.service.StudentsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("students")
@RequiredArgsConstructor
@Slf4j
public class StudentsController {

    private final StudentsService studentsService;

    @PostMapping
    public ResponseEntity<StudentResponse> saveStudent(@RequestHeader(name = "Authorization") String token, @RequestBody NewStudentRequest newStudentRequest){
        log.info("POST /users -> 201 CREATED");
        return ResponseEntity.ok(studentsService.studentsRegistration(newStudentRequest,token.substring(7)));
    }
}

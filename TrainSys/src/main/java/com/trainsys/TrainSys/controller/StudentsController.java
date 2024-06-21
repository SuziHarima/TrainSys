package com.trainsys.TrainSys.controller;


import com.trainsys.TrainSys.controller.request.NewStudentRequest;
import com.trainsys.TrainSys.controller.response.SearchStudentsResponse;
import com.trainsys.TrainSys.controller.response.StudentResponse;
import com.trainsys.TrainSys.entity.StudentsEntity;
import com.trainsys.TrainSys.service.StudentsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<SearchStudentsResponse>> listAll(@RequestHeader(name = "Authorization") String token){
        log.info("GET /exercises -> OK");
        return ResponseEntity.ok(studentsService.searchAll(token.substring(7)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeStudentById(@RequestHeader(name = "Authorization") String token, @PathVariable Integer id){
        studentsService.removeById(id, token.substring(7));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateStudent(@RequestHeader(name = "Authorization") String token, @PathVariable Integer id, @RequestBody StudentsEntity student){
        studentsService.update(token.substring(7), id, student);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

package com.trainsys.TrainSys.service;



import com.trainsys.TrainSys.controller.request.NewStudentRequest;
import com.trainsys.TrainSys.controller.response.SearchStudentsResponse;
import com.trainsys.TrainSys.controller.response.StudentResponse;
import com.trainsys.TrainSys.entity.StudentsEntity;
import com.trainsys.TrainSys.entity.UserEntity;
import com.trainsys.TrainSys.repository.StudentsRepository;
import com.trainsys.TrainSys.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class StudentsService {

    private final StudentsRepository studentsRepository;
    private final UserRepository userRepository;
    private final TokenService tokenService;

    public StudentResponse studentsRegistration(NewStudentRequest newStudentRequest, String token) {
        //valida papel de usuario
        String userRole =  tokenService.fieldSearch(token, "scope");
        if (!Objects.equals(userRole, "ADM")){
            throw new RuntimeException("Usuario não tem acesso a essa funcionalidade");
        }

        Integer userId = Integer.valueOf(tokenService.fieldSearch(token, "sub"));
        UserEntity user = userRepository.findById(userId).orElseThrow(() ->
                new RuntimeException("Usuário não encontrado"));

        StudentsEntity student = new StudentsEntity();
        student.setName(newStudentRequest.name());
        student.setEmail(newStudentRequest.email());
        student.setDateBirth(newStudentRequest.dateBirth());
        student.setCpf(newStudentRequest.cpf());
        student.setContact(newStudentRequest.contact());
        student.setCity(newStudentRequest.city());
        student.setNeighborhood(newStudentRequest.neighborhood());
        student.setNumber(newStudentRequest.number());
        student.setStreet(newStudentRequest.street());
        student.setState(newStudentRequest.state());
        student.setCep(newStudentRequest.cep());
        student.setUser(user);

        studentsRepository.save(student);

        return new StudentResponse();
    }

    public List<SearchStudentsResponse> searchAll(String token) {
        String userRole = tokenService.fieldSearch(token, "scope");
        if (!Objects.equals(userRole, "ADM")) {
            throw new RuntimeException("Usuario não tem acesso a essa funcionalidade");
        }
        List<StudentsEntity> students =  studentsRepository.findAll();

        return students.stream().map(s -> new SearchStudentsResponse(s.getId(), s.getName(), s.getEmail(), s.getDateBirth(), s.getCpf(), s.getContact(), s.getCity(), s.getNeighborhood(), s.getNumber(), s.getStreet(), s.getState(), s.getCep())).toList();
    }

}

package com.trainsys.TrainSys.repository;


import com.trainsys.TrainSys.entity.StudentsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentsRepository extends JpaRepository<StudentsEntity, Integer> {
}

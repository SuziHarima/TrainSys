package com.trainsys.TrainSys.repository;


import com.trainsys.TrainSys.entity.ExercisesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExercisesRepository extends JpaRepository<ExercisesEntity, Integer> {
}

package com.trainsys.TrainSys.repository;


import com.trainsys.TrainSys.entity.WorkoutsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkoutsRepository extends JpaRepository<WorkoutsEntity, Integer> {
}

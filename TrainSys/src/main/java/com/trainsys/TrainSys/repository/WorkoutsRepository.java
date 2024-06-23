package com.trainsys.TrainSys.repository;


import com.trainsys.TrainSys.entity.WorkoutsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkoutsRepository extends JpaRepository<WorkoutsEntity, Integer> {
    List<WorkoutsEntity> findAllByStudentId(@Param("id") Integer id);
}

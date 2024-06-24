package com.trainsys.TrainSys.repository;


import com.trainsys.TrainSys.entity.StudentsEntity;
import com.trainsys.TrainSys.entity.UserEntity;
import com.trainsys.TrainSys.entity.WorkoutsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentsRepository extends JpaRepository<StudentsEntity, Integer> {
//    List<StudentsEntity> findAllByUserId (@Param("user_id") Integer user);
}

package com.example.software.repository;

import com.example.software.pojo.TeacherEvaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TeacherEvaluationRepository extends JpaRepository<TeacherEvaluation, String> {
    @Query("select t from TeacherEvaluation t where t.userId = ?1")
    List<TeacherEvaluation> findByUserId(String userId);

    //@Query("select t from TeacherEvaluation t where t.userId = ?1")
    List<TeacherEvaluation> getTeacherEvaluationsByUserId(String userId);
}
package com.example.software.repository;

import com.example.software.pojo.Programs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ProgramsRepository extends JpaRepository<Programs, Integer>, JpaSpecificationExecutor<Programs> {
    List<Programs> findByMajorId(Integer majorId);
}
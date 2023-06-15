package com.example.software.repository;

import com.example.software.pojo.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, String> {

    @Query("select c from Course c")
    List<Course> getCourseList();

    @Query("select c from Course c where c.courseId = ?1")
    Course getCourseByCourseId(String courseId);

    @Transactional
    @Modifying
    @Query("update Course c set c.courseNum = 0 where c.courseNum = 1")
    void updateNumToZeroFinal();
}

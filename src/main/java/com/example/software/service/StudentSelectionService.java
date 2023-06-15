package com.example.software.service;

import com.example.software.pojo.StudentSelection;
import com.example.software.repository.StudentSelectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentSelectionService {
    @Autowired
    private StudentSelectionRepository studentSelectionRepository;

    public StudentSelectionService(StudentSelectionRepository studentSelectionRepository) {
        this.studentSelectionRepository = studentSelectionRepository;
    }

    public void saveStudentSelection(String userId) {
        StudentSelection studentSelection = new StudentSelection();
        studentSelection.setUserId(userId);
        studentSelection.setStatus(1);
        studentSelectionRepository.save(studentSelection);
    }

    public StudentSelection getStudentSelectionByUserId(String userId) {
        return studentSelectionRepository.getStudentSelectionByUserId(userId);
    }
}

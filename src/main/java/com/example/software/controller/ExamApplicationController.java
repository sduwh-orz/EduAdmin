package com.example.software.controller;

import com.example.software.response.Response;
import com.example.software.service.ClassroomService;
import com.example.software.service.ExamApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(path = "/examApplication")
public class ExamApplicationController {
    @Autowired
    private ExamApplicationService examApplicationService;

    public ExamApplicationController(ExamApplicationService examApplicationService) {
        this.examApplicationService = examApplicationService;
    }
    @PostMapping(path = "/examApplication")
    public synchronized Response examapplication(
            @RequestParam String majorId,
            @RequestParam String courseId,
            @RequestParam String examFormat
    ){
        examApplicationService.applyExamByMajorCourseAndFormat(
                majorId,
                courseId,
                examFormat);
        return new Response(true, "", null);
    }

    @GetMapping("/getExamList")
    public synchronized Response getExamList() {
        return new Response(true, "", examApplicationService.listAllApplication());
    }
}

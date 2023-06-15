package com.example.software.controller;

import com.example.software.pojo.Course;
import com.example.software.pojo.CourseApplication;
import com.example.software.pojo.CourseApplicationRepository;
import com.example.software.pojo.User;
import com.example.software.repository.UserRepository;
import com.example.software.response.Response;
import com.example.software.service.CourseApplicationService;
import com.example.software.service.CourseService;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(path = "/course")
public class CourseController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private CourseApplicationService courseApplicationService;
    @Autowired
    private UserRepository userRepository;

    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping("/accept")
    public synchronized Response acceptCourse(HttpSession session, @RequestParam String courseId) {
        if(session.getAttribute("user") == null)
            return new Response(false, "Please login first", null);
        User user = userRepository.findById((String) session.getAttribute("user")).orElse(null);
        if(!user.getUserType().equals("admin"))
            return new Response(false, "Permission denied", null);
        if(courseApplicationService.findById(courseId) == null)
            return new Response(false, "Course not found", null);
        try {
            CourseApplication courseApplication = courseApplicationService.findById(courseId);
            Course course = new Course();
            course.setCourseId(courseId);
            course.setCourseName(courseApplication.getCourseName());
            course.setCourseTeacherId(courseApplication.getCourseTeacherId());
            course.setCourseNum(courseApplication.getCourseNum());
            courseService.saveCourse(course);
            courseApplicationService.deleteCourseApplication(courseId);
            return new Response(true, "Accept successfully", null);
        } catch (Exception e) {
            return new Response(false, "Accept failed", null);
        }
    }

    @PostMapping("/reject")
    public synchronized Response rejectCourse(HttpSession session, @RequestParam String courseId) {
        if(session.getAttribute("user") == null)
            return new Response(false, "Please login first", null);
        User user = userRepository.findById((String) session.getAttribute("user")).orElse(null);
        if(!user.getUserType().equals("admin"))
            return new Response(false, "Permission denied", null);
        if(courseApplicationService.findById(courseId) == null)
            return new Response(false, "Course not found", null);
        if(courseApplicationService.deleteCourseApplication(courseId))
            return new Response(true, "Reject successfully", null);
        return new Response(false, "Reject failed", null);
    }
}

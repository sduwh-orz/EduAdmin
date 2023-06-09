package com.example.software.pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class ScoreId implements Serializable {

    @Column(name = "user_id", nullable = false)
    private String userId;
    @Column(name = "course_id", nullable = false)
    private String courseId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }
}

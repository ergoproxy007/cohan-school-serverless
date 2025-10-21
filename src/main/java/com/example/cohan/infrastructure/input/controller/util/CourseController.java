package com.example.cohan.infrastructure.input.controller.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Course>> getAllCourses() {
        List<Course> courses = allCourses();
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    private List<Course> allCourses() {
        // Mock data - in a real application, this would come from a service/repository
        Course course1 = new Course();
        course1.setId(1);
        course1.setName("Introduction to Java & Spring");
        course1.setPrice(55.0);

        Course course2 = new Course();
        course2.setId(2);
        course2.setName("Advanced Spring Boot Java");
        course2.setPrice(99.0);

        return Arrays.asList(course1, course2);
    }
}

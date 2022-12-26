package com.universityW3.controller;

import com.universityW3.model.Course;
import com.universityW3.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;

@RestController
public class CourseController {

    @Autowired
    CourseService courseService;

    @GetMapping(value = "/find-course/{course}")
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseEntity<Course> findCourse(@PathVariable(name = "course", required = true) String courseName) {
        if (courseService.findCourse(courseName) != null) {
            Course curso = new Course();
            curso = courseService.findCourse(courseName);
            return new ResponseEntity("Course: " + curso.getNombre() + " found", HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity("Course not found", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/new-course")
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseEntity<Course> newCourse(@RequestBody Course course) {
        if (courseService.findCourse(course.getNombre()) != null) {
            return new ResponseEntity("Course already exist!!", HttpStatus.BAD_REQUEST);
        } else {
            courseService.createCourse(course);
            return new ResponseEntity("Course created with name: " + course.getNombre() + ", and id: " + course.getIdCurso(), HttpStatus.ACCEPTED);
        }
    }

    @PatchMapping(value = "/update-course")
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseEntity<Course> updateUser(@RequestBody Course course) {
        if (courseService.findCourse(course.getNombre()) != null) {
            Course newCourse = courseService.findCourse(course.getNombre());
            courseService.updateCourse(course);
            return new ResponseEntity("Course: " + course.getNombre() + " properly updated", HttpStatus.ACCEPTED);
        }
        if (courseService.findCourse(course.getNombre()) == null) {
            return new ResponseEntity("Course doesn't exist!!", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity("Course couldn't be updated", HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping(value = "/delete-course")
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseEntity<Course> deleteUser(@RequestBody Course course) {
        if (courseService.findCourse(course.getNombre()) != null) {
            Course newCourse = courseService.findCourse(course.getNombre());
            courseService.deleteCourse(course);
            return new ResponseEntity("Course: " + course.getNombre() + " properly deleted", HttpStatus.ACCEPTED);
        }
        if (courseService.findCourse(course.getNombre()) == null) {
            return new ResponseEntity("Course doesn't exist!!", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity("Course couldn't be deleted", HttpStatus.BAD_REQUEST);
        }
    }
}
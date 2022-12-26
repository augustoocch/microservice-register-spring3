package com.universityW3.service;

import com.universityW3.model.Admin;
import com.universityW3.model.Course;
import com.universityW3.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service("courseService")
public class CourseServiceImpl implements CourseService{

    @Autowired
    CourseRepository courseRepository;

    @Override
    public void deleteCourse(Course course) {
        courseRepository.delete(course);
    }

    @Override
    public void createCourse(Course course) {
        courseRepository.save(course);
    }

    @Override
    public void updateCourse(Course course) {
        courseRepository.save(course);
    }

    @Override
    public Course findCourse(String name) {
        List<Course> allCourses = courseRepository.findAll();
        try {
            List<Course> listAndFind = allCourses
                    .stream()
                    .filter(a -> ((a.getNombre()).equals(name)))
                    .collect(Collectors.toList());
            if (listAndFind.size() == 0) {
                return null;
            }
            return listAndFind.get(0);
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace(System.out);
        }
        return null;
    }
}

package com.example.ims.service;

import com.example.ims.model.*;
import com.example.ims.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CourseService implements CourseRepository {

    @Autowired
    private CourseJpaRepository courseJpaRepository;

    @Override
    public List<Course> getCourses() {
        return courseJpaRepository.findAll();
    }

    @Override
    public Course getCourseById(Long id) {
        return courseJpaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found with id: " + id));
    }

    @Override
    public Course addCourse(Course course) {
        return courseJpaRepository.save(course);
    }

    @Override
    public Course updateCourse(Course course) {
        if (course.getId() == null || !courseJpaRepository.existsById(course.getId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found with id: " + course.getId());
        }
        return courseJpaRepository.save(course);
    }

    @Override
    public void deleteCourse(Long id) {
        try {
            Course course = getCourseById(id);
            if (!course.getStudents().isEmpty()) {
                course.getStudents().forEach(student -> student.setCourse(null));
                course.setStudents(List.of()); 
            }
            courseJpaRepository.deleteById(id);
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        } catch (ResponseStatusException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to delete course", ex);
        }
    }

    @Override
    public List<Course> findByInstructor(Instructor instructor) {
        return courseJpaRepository.findByInstructor(instructor);
    }
}
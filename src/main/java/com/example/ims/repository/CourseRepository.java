package com.example.ims.repository;

import com.example.ims.model.Course;
import com.example.ims.model.Instructor;
import java.util.List;

public interface CourseRepository {
    List<Course> getCourses(); 
    Course getCourseById(Long id);
    Course addCourse(Course course); 
    Course updateCourse(Course course); 
    void deleteCourse(Long id); 
    List<Course> findByInstructor(Instructor instructor); 
}
package com.example.ims.repository;

import com.example.ims.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseJpaRepository extends JpaRepository<Course, Long> {
    // Add findByInstructor() method
    List<Course> findByInstructor(Instructor instructor);
}
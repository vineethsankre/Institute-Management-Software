package com.example.ims.repository;

import com.example.ims.model.Course;
import com.example.ims.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentJpaRepository extends JpaRepository<Student, Long> {
    List<Student> findByCourse(Course course);
}
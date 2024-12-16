package com.example.ims.repository;

import com.example.ims.model.Course;
import com.example.ims.model.Student;
import java.util.List;

public interface StudentRepository {
    List<Student> getStudents(); 
    Student getStudentById(Long id);
    Student addStudent(Student student); 
    Student updateStudent(Student student);
    void deleteStudent(Long id); 
    List<Student> findByCourse(Course course); 
}
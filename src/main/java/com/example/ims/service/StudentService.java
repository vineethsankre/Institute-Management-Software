package com.example.ims.service;

import com.example.ims.model.*;
import com.example.ims.repository.CourseJpaRepository;
import com.example.ims.repository.StudentJpaRepository;
import com.example.ims.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class StudentService implements StudentRepository {

    @Autowired
    private StudentJpaRepository studentJpaRepository;

    @Autowired
    private CourseJpaRepository courseJpaRepository;

    @Override
    public List<Student> getStudents() {
        return studentJpaRepository.findAll();
    }

    @Override
    public Student getStudentById(Long id) {
        return studentJpaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found with id: " + id));
    }

    @Override
    public Student addStudent(Student student) {
        return studentJpaRepository.save(student);
    }

    @Override
    public Student updateStudent(Student student) {
        if (student.getId() == null || !studentJpaRepository.existsById(student.getId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found with id: " + student.getId());
        }
        return studentJpaRepository.save(student);
    }

    @Override
    public void deleteStudent(Long id) {
        try {
            Student student = getStudentById(id);
            if (student.getCourse() != null) {
                student.getCourse().getStudents().remove(student);
                courseJpaRepository.save(student.getCourse());
            }
            studentJpaRepository.deleteById(id);
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        } catch (ResponseStatusException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to delete student", ex);
        }
    }

    @Override
    public List<Student> findByCourse(Course course) {
        return studentJpaRepository.findByCourse(course);
    }
}
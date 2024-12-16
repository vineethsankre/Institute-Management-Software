package com.example.ims.service;

import com.example.ims.model.*;
import com.example.ims.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class InstructorService implements InstructorRepository {

    @Autowired
    private InstructorJpaRepository instructorJpaRepository;

    @Autowired
    private CourseJpaRepository courseJpaRepository;

    @Override
    public List<Instructor> getInstructors() {
        return instructorJpaRepository.findAll();
    }

    @Override
    public Instructor getInstructorById(Long id) {
        return instructorJpaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Instructor not found with id: " + id));
    }

    @Override
    public Instructor addInstructor(Instructor instructor) {
        return instructorJpaRepository.save(instructor);
    }

    @Override
    public Instructor updateInstructor(Instructor instructor) {
        if (instructor.getId() == null || !instructorJpaRepository.existsById(instructor.getId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Instructor not found with id: " + instructor.getId());
        }
        return instructorJpaRepository.save(instructor);
    }

    @Override
    public void deleteInstructor(Long id) {
        try {
            Instructor instructor = getInstructorById(id);
            List<Course> courses = courseJpaRepository.findByInstructor(instructor);
            if (!courses.isEmpty()) {
                courses.forEach(course -> {
                    course.setInstructor(null);
                    courseJpaRepository.save(course);
                });
            }
            instructorJpaRepository.deleteById(id);
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        } catch (ResponseStatusException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to delete instructor", ex);
        }
    }
}
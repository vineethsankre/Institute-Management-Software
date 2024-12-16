package com.example.ims.repository;

import com.example.ims.model.Instructor;
import java.util.List;

public interface InstructorRepository {
    List<Instructor> getInstructors(); 
    Instructor getInstructorById(Long id);
    Instructor addInstructor(Instructor instructor); 
    Instructor updateInstructor(Instructor instructor);
    void deleteInstructor(Long id);
}
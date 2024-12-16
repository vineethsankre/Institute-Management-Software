package com.example.ims.repository;

import com.example.ims.model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstructorJpaRepository extends JpaRepository<Instructor, Long> { }
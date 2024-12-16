package com.example.ims.controller;

import com.example.ims.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private InstructorService instructorService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("totalStudents", studentService.getStudents().size());
        model.addAttribute("totalCourses", courseService.getCourses().size());
        model.addAttribute("totalInstructors", instructorService.getInstructors().size());
        return "home"; 
    }
}
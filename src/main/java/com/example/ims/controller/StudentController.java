package com.example.ims.controller;

import com.example.ims.model.Student;
import com.example.ims.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    @GetMapping
    public String getAllStudents(Model model) {
        model.addAttribute("students", studentService.getStudents());
        return "students";
    }

    @GetMapping("/{id}")
    public String getStudentById(@PathVariable Long id, Model model) {
        model.addAttribute("student", studentService.getStudentById(id));
        return "student-details";
    }

    @GetMapping("/add")
    public String addStudentForm(Model model) {
        model.addAttribute("student", new Student());
        model.addAttribute("courses", courseService.getCourses());
        return "add-student";
    }

    @PostMapping("/add")
    public String addStudent(@ModelAttribute Student student, RedirectAttributes redirectAttributes) {
        Long courseId = student.getCourse().getId();
        student.setCourse(courseService.getCourseById(courseId));
        studentService.addStudent(student);
        redirectAttributes.addFlashAttribute("message", "Student added successfully!");
        return "redirect:/students";
    }

    @GetMapping("/edit/{id}")
    public String editStudentForm(@PathVariable Long id, Model model) {
        model.addAttribute("student", studentService.getStudentById(id));
        model.addAttribute("courses", courseService.getCourses());
        return "edit-student";
    }

    @PostMapping("/edit/{id}")
    public String updateStudent(@PathVariable Long id, @ModelAttribute Student student, RedirectAttributes redirectAttributes) {
        student.setId(id);
        Long courseId = student.getCourse().getId();
        student.setCourse(courseService.getCourseById(courseId));
        studentService.updateStudent(student);
        redirectAttributes.addFlashAttribute("message", "Student updated successfully!");
        return "redirect:/students";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        studentService.deleteStudent(id);
        redirectAttributes.addFlashAttribute("message", "Student deleted successfully!");
        return "redirect:/students";
    }
}
package com.example.ims.controller;

import com.example.ims.model.Course;
import com.example.ims.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private InstructorService instructorService;

    @GetMapping
    public String getAllCourses(Model model) {
        model.addAttribute("courses", courseService.getCourses());
        return "courses";
    }

    @GetMapping("/{id}")
    public String getCourseById(@PathVariable Long id, Model model) {
        model.addAttribute("course", courseService.getCourseById(id));
        return "course-details";
    }

    @GetMapping("/add")
    public String addCourseForm(Model model) {
        model.addAttribute("course", new Course());
        model.addAttribute("instructors", instructorService.getInstructors());
        return "add-course";
    }

    @PostMapping("/add")
    public String addCourse(@ModelAttribute Course course, RedirectAttributes redirectAttributes) {
        Long instructorId = course.getInstructor().getId();
        course.setInstructor(instructorService.getInstructorById(instructorId));
        courseService.addCourse(course);
        redirectAttributes.addFlashAttribute("message", "Course added successfully!");
        return "redirect:/courses";
    }

    @GetMapping("/edit/{id}")
    public String editCourseForm(@PathVariable Long id, Model model) {
        model.addAttribute("course", courseService.getCourseById(id));
        model.addAttribute("instructors", instructorService.getInstructors());
        return "edit-course";
    }

    @PostMapping("/edit/{id}")
    public String updateCourse(@PathVariable Long id, @ModelAttribute Course course, RedirectAttributes redirectAttributes) {
        course.setId(id);
        Long instructorId = course.getInstructor().getId();
        course.setInstructor(instructorService.getInstructorById(instructorId));
        courseService.updateCourse(course);
        redirectAttributes.addFlashAttribute("message", "Course updated successfully!");
        return "redirect:/courses";
    }

    @GetMapping("/delete/{id}")
    public String deleteCourse(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        courseService.deleteCourse(id);
        redirectAttributes.addFlashAttribute("message", "Course deleted successfully!");
        return "redirect:/courses";
    }
}
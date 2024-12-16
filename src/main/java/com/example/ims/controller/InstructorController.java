package com.example.ims.controller;

import com.example.ims.model.*;
import com.example.ims.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/instructors")
public class InstructorController {

    @Autowired
    private InstructorService instructorService;

    @GetMapping
    public String getAllInstructors(Model model) {
        model.addAttribute("instructors", instructorService.getInstructors());
        return "instructors";
    }

    @GetMapping("/{id}")
    public String getInstructorById(@PathVariable Long id, Model model) {
        model.addAttribute("instructor", instructorService.getInstructorById(id));
        return "instructor-details";
    }

    @GetMapping("/add")
    public String addInstructorForm(Model model) {
        model.addAttribute("instructor", new Instructor());
        return "add-instructor";
    }

    @PostMapping("/add")
    public String addInstructor(@ModelAttribute Instructor instructor, RedirectAttributes redirectAttributes) {
        instructorService.addInstructor(instructor);
        redirectAttributes.addFlashAttribute("message", "Instructor added successfully!");
        return "redirect:/instructors";
    }

    @GetMapping("/edit/{id}")
    public String editInstructorForm(@PathVariable Long id, Model model) {
        model.addAttribute("instructor", instructorService.getInstructorById(id));
        return "edit-instructor";
    }

    @PostMapping("/edit/{id}")
    public String updateInstructor(@PathVariable Long id, @ModelAttribute Instructor instructor, RedirectAttributes redirectAttributes) {
        instructor.setId(id); 
        instructorService.updateInstructor(instructor);
        redirectAttributes.addFlashAttribute("message", "Instructor updated successfully!");
        return "redirect:/instructors";
    }

    @GetMapping("/delete/{id}")
    public String deleteInstructor(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        instructorService.deleteInstructor(id);
        redirectAttributes.addFlashAttribute("message", "Instructor deleted successfully!");
        return "redirect:/instructors";
    }
}
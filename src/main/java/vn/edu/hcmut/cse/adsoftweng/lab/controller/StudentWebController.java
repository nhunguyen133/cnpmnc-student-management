package vn.edu.hcmut.cse.adsoftweng.lab.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import vn.edu.hcmut.cse.adsoftweng.lab.service.StudentService;
import vn.edu.hcmut.cse.adsoftweng.lab.entity.Student;

@Controller
@RequestMapping("/students")
public class StudentWebController {
    @Autowired
    private StudentService service;
    
    @GetMapping
    public String getAllStudents(Model model) {
        List<Student> students = service.getAll();

        model.addAttribute("dsSinhVien", students);

        return "students";
    }
}
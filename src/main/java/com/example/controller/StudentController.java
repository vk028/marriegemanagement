package com.example.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.entity.Student;
import com.example.repository.StudentRepository;

import ch.qos.logback.core.model.Model;

@Controller
public class StudentController {

	@Autowired
	private StudentRepository studentRepository;
	
	
	//index page 
	@GetMapping("/")
	public String index() {
		
		return "index";
	}
	
	//for register student 
	@GetMapping("/register")
	public String register(Student student)
	{
		return "register";
	}
	
	//for save the student in database 
	@PostMapping("/saveStudent")
	public String saveStudent(@ModelAttribute Student student) {
		studentRepository.save(student);
		return "saved";
	}
	// get all student data
	@GetMapping("/getStudent")
	public String getAllStudent(org.springframework.ui.Model model) {
		List<Student> list=studentRepository.findAll();
	    model.addAttribute("studentlist", list);
		return "showStudentData";
	}
	
	//get one or single data
	@GetMapping("/getsingledata/{id}")
	public String singleData(@PathVariable("id") int id, org.springframework.ui.Model model ) {
		
		Optional<Student> fin=studentRepository.findById((long) id);
		Student student=fin.get();
		model.addAttribute("stu", student);
		
		return "getSingleData";
	}
	
}

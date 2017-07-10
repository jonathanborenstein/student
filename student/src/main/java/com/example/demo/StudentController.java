package com.example.demo;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.model.Guide;
import com.example.model.GuideRepository;
import com.example.model.Student;
import com.example.model.StudentRepository;
import com.example.model.SubjectRepository;

@Controller
public class StudentController {
	
	private final StudentRepository student;
	private final GuideRepository guide;
	private final SubjectRepository subjectRepository;

	
	public StudentController(StudentRepository student, GuideRepository guide, SubjectRepository subjectRepository) {
		this.student = student;
		this.guide = guide;
		this.subjectRepository = subjectRepository;
	}


	@GetMapping("/addstudent")
	public String addStudent(Model model) {
		Student student = new Student();
		model.addAttribute("student", student);
		List<Guide> guides = guide.findAll();
		model.addAttribute("guides", guides);
		model.addAttribute("subjects", subjectRepository.findAll());
		return "addstudent";
	}
	

	@PostMapping("/addstudent")
	public String addStudent(Student student, BindingResult result, Model model){
		
		if (student.getName().equals("") || student.getName().matches(".*\\d+.*")) {
			return "redirect:/addstudent";
		}
		
		this.student.save(student);
		return "redirect:/allstudents";
	}
	
	@RequestMapping(value = {"/allstudents", "/"}, method = RequestMethod.GET) 
	public String allStudents(ModelMap model){
		model.put("students", student.findAll());
		model.put("subjects", subjectRepository.findAll());
		return "allstudents";
	}
	

}

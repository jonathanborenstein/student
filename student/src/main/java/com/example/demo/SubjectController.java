package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.model.Subject;
import com.example.model.SubjectRepository;

@Controller
public class SubjectController {
	
	private final SubjectRepository subjectRepository;

	public SubjectController(SubjectRepository subjectRepository) {
		this.subjectRepository = subjectRepository;
	}

	@GetMapping("/addsubject")
	public String addSubject(Model model) {
		Subject subject = new Subject();
		model.addAttribute("subject", subject);
		return "addsubject";
	}

	@PostMapping("/addsubject")
	public String addSubject(Subject subject, BindingResult result) {
		
		if (subject.getName().equals("") || subject.getName().matches(".*\\d+.*")) {
			result.rejectValue("name", "name");
			return "addsubject";
		}
		
		this.subjectRepository.save(subject);
		return "redirect:/allstudents";
	}

}

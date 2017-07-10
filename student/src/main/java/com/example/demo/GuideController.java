package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.model.Guide;
import com.example.model.GuideRepository;

@Controller
public class GuideController {

	private final GuideRepository guideRepository;

	public GuideController(GuideRepository guideRepository) {
		this.guideRepository = guideRepository;
	}

	@GetMapping("/addguide")
	public String addGuide(Model model) {
		Guide guide = new Guide();
		model.addAttribute("guide", guide);
		return "addguide";
	}

	@PostMapping("/addguide")
	public String addGuide(Guide guide, BindingResult result) {
		
		if (guide.getName().equals("") || guide.getName().matches(".*\\d+.*")) {
			result.rejectValue("name", "name");
			return "addguide";
		}
		
		this.guideRepository.save(guide);
		return "redirect:/allstudents";
	}

}
package com.anime.weebhaven;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.anime.weebhaven.weebhaven.user;

@Controller
public class DemoController {

	@RequestMapping("index")
	public String index() {
		return "index";
	}

	@GetMapping("/register")
	public String showSignUpForm(Model model) {
		model.addAttribute("user", new user());
		return "index.html";
	}

}

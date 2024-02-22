package com.anime.weebhaven.weebhaven;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class DemoController {

	// @RequestMapping("index")
	// public String index() {
	// return "index";
	// }

	@Autowired
	private RegistrationService registrationService;

	@PostMapping
	@ResponseBody
	public String registerUser(@ModelAttribute user user) {
		registrationService.saveRegistrationDetails(user);
		return "User registered successfully!";
	}

}

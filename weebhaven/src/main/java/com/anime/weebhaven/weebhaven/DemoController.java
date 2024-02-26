package com.anime.weebhaven.weebhaven;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DemoController {

	@Autowired
	private RegistrationService registrationService;

	@GetMapping("/")
	public String index() {
		return "index";
	}

	@PostMapping
	@ResponseBody
	@RequestMapping("/register")
	public ResponseEntity<String> registerUser(@ModelAttribute user user) {
		registrationService.saveRegistrationDetails(user);

		// Return a JSON response with the success message
		String message = "User registered successfully!";
		return new ResponseEntity<>(message, HttpStatus.OK);
	}

}

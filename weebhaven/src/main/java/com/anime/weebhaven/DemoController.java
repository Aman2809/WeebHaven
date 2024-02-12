package com.anime.weebhaven;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.anime.weebhaven.weebhaven.UserRepository;
import com.anime.weebhaven.weebhaven.user;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
public class DemoController {

	@RequestMapping("index")
	public String index() {
		return "index";
	}

	@Autowired
	private UserRepository repo;
	
	@PostMapping("/register")
	public ResponseEntity<user>  showSignUpForm(  @RequestBody  user user ) {
		repo.save(user);

		return ResponseEntity.ok(user);
	}

}

package com.anime.weebhaven.weebhaven;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DemoController {

	// ApplicationContext ctx = new
	// ClassPathXmlApplicationContext("applicationContext.xml");

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private RegistrationService registrationService;

	@GetMapping("/index")
	public String index() {
		return "index.html";
	}

	@GetMapping("/admin/home")
	public String getAdmin() {
		return "home_admin";
	}

	@GetMapping("/user/home")
	public String getUser() {
		return "home_user";
	}

	@GetMapping("/index/header")
	public String header() {
		return "header";
	}

	@GetMapping("/index/homepage")
	public String homepage() {
		return "FirstPage/homepage";
	}

	@GetMapping("/index/video")
	public String video() {
		return "VideoViewerPage/video";
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

	@PostMapping
	@ResponseBody
	@RequestMapping("/login")
	public ResponseEntity<String> LoginUser(@ModelAttribute user us) {
		String message;
		if (isValid(us)) {
			System.out.println("***********valid user**********");
			message = "User registered successfully!";
		} else {
			System.out.println("***********invalid user**********");
			message = "wrong credentials";
		}

		// Return a JSON response with the success message
		return new ResponseEntity<>(message, HttpStatus.OK);
	}

	// database testing

	public List<user> getAllUsers() {
		String sql = "SELECT  id, email, password, username, role FROM users";
		return jdbcTemplate.query(sql, new UserRowMapper());
	}

	public void adduser(user u) {
		String sql = "INSERT INTO weebhavendb.users ( id , email , password , username , role ) VALUES ( \"" + u.getId()
				+ "\" , \"" + u.getEmail() + "\" , \"" + u.getPassword() + "\" , \"" + u.getUsername() + "\" , \""
				+ u.getRole() + "\" )";

		int rowsAffected = jdbcTemplate.update(sql);

		if (rowsAffected > 0) {
			System.out.println("*****User added successfully!*****");
		} else {
			System.out.println("Failed to add user.");
		}
	}

	// for checking is user is added is out database

	public boolean isValid(user us) {
		boolean isValid = false;
		try {
			List<user> userlist = getAllUsers();
			if (userlist != null && userlist.size() > 0)
				for (user u : userlist) {
					int status = us.compare(u);
					if (status == user.VALID_USER) {
						isValid = true;
						break;
					}
				}

			else
				System.err.println("USERLIST IS NULL");
		} catch (

		Exception e) {
			System.err.println("index  ERROR:" + e.toString());
		}

		return isValid;
	}// end of isValid user

	private static class UserRowMapper implements RowMapper<user> {
		@Override
		public user mapRow(ResultSet rs, int rowNum) throws SQLException {
			user user = new user();
			user.setId(rs.getLong("id"));
			user.setEmail(rs.getString("email"));
			user.setPassword(rs.getString("password"));
			user.setUsername(rs.getString("username"));
			user.setRole(rs.getString("role"));
			return user;
		}
	}

}

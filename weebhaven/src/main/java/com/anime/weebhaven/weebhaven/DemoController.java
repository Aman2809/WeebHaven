package com.anime.weebhaven.weebhaven;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

		try {
			List<user> userlist = getAllUsers();
			if (userlist != null && userlist.size() > 0)
				for (user u : userlist) {
					System.out.println(u.toString());
				}
			else
				System.err.println("USERLIST IS NULL");
		} catch (Exception e) {
			System.err.println("index :" + e.toString());
		}

		return "index";
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

	// database testing

	public List<user> getAllUsers() {
		String sql = "SELECT  id email password username role FROM users";
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

	public boolean isValid(user u) {

		return true;
	}

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

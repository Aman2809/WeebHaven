// package com.anime.weebhaven.weebhaven;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.core.userdetails.User;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import
// org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.stereotype.Service;
// import java.util.Optional;

// @Service
// public class MyUserDetailsService implements UserDetailsService {

// @Autowired
// private UserRepository repo;

// @Override
// public UserDetails loadUserByUsername(String username) throws
// UsernameNotFoundException {
// Optional<user> user = repo.findByUsername(username);
// if (user.isPresent()) {
// var userObj = user.get();
// return User.builder()
// .username(userObj.getUsername())
// .password(userObj.getPassword())
// .roles(getRoles(userObj))
// .build();
// } else {
// throw new UsernameNotFoundException("User not found: " + username);
// }

// }

// private String[] getRoles(user user) {
// if (user.getRole() == null) {
// return new String[] { "USER" };
// }
// return user.getRole().split(",");

// }
// }

// package com.anime.weebhaven.weebhaven;

// import java.util.ArrayList;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import
// org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.stereotype.Service;

// @Service
// public class MyUserDetailsService implements UserDetailsService {

// @Autowired
// private UserRepository repo;

// @Override
// public UserDetails loadUserByUsername(String username) throws
// UsernameNotFoundException {
// user user = repo.findByUsername(username);
// if (user == null) {
// throw new UsernameNotFoundException("User not found: " + username);
// }
// return new
// org.springframework.security.core.userdetails.User(user.getUsername(),
// user.getPassword(),
// new ArrayList<>());
// }
// }

package com.anime.weebhaven.weebhaven;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {

	@Autowired
	private UserRepository repo;

	@Autowired
	private TestEntityManager entityManager;

	@Test
	public void TestCreateUser() {
		user user = new user();
		user.setEmail("jhaaman1005@gmail.com");
		user.setPassword("aman2003");
		user.setUsername("aman");

		user savedUser = repo.save(user);// Adding a new user into the database

		user existUser = entityManager.find(user.class, savedUser.getId());
		assertThat(existUser.getEmail()).isEqualTo(user.getEmail());
	}

}

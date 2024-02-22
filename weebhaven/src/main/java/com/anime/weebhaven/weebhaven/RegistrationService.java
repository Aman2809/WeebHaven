package com.anime.weebhaven.weebhaven;

// RegistrationService.java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    @Autowired
    private UserRepository repo;

    public void saveRegistrationDetails(user user) {
        repo.save(user);
    }
}

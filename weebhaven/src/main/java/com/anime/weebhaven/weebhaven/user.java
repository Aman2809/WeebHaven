package com.anime.weebhaven.weebhaven;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.val;

@Entity
@Table(name = "users")
public class user {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String username;

    @Column(nullable = false, unique = true, length = 45)
    private String email;

    @Column(nullable = false, length = 64)
    private String password;

    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public user() {
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public user(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String toString() {
        return this.username + " " + this.id + " " + this.password;
    }

    public static int USERNAME_NOT_MACHED = 0x67;
    public static int PASSWORD_NOT_MACHED = 0x56;
    public static int VALID_USER = 0x578;

    public int compare(user u) {
        int output = 0x00;
        if (u.getUsername().compareTo(this.username) == 0)
            if (u.getPassword().compareTo(this.password) == 0)
                output = VALID_USER;
            else
                output = PASSWORD_NOT_MACHED;
        else
            output = USERNAME_NOT_MACHED;

        return output;
    }

}

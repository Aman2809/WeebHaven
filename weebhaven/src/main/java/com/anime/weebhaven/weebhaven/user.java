package com.anime.weebhaven.weebhaven;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

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

    private String user_hisyory;

    public String getUserHistory() {
        return user_hisyory;
    }

    public List<String> getUserHistoryList() {
        return Arrays.asList(user_hisyory.split(","));
    }

    public void addHistory(String data) {
        if (user_hisyory == null)
            user_hisyory = data;
        else
            user_hisyory = user_hisyory + "," + data;
    }

    public List<VideoItem> getAbsoluteHistory(JdbcTemplate Jdbctemplate) {
        List<VideoItem> list = new ArrayList<>();
        List<String> history = getUserHistoryList();

        for (String id : history) {
            list.add(getVideoItemByID(id, Jdbctemplate));
        }
        return list;
    }

    public VideoItem getVideoItemByID(String id, JdbcTemplate jdbcTemplate) {
        String sql = "SELECT  id, name, poster_path, video_path FROM videodata WHERE id = " + id;
        return jdbcTemplate.queryForObject(sql, new VideoItemRowMapper());
    }

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

    public void invalidate(JdbcTemplate jdbcTemplate) {
        // String sql = "UPDATE users SET password = ? WHERE id = ?";
        // jdbcTemplate.update(sql, password, id);

        // sql = "UPDATE users SET role = ? WHERE id = ?";
        // jdbcTemplate.update(sql, role, id);

        String sql = "UPDATE users SET string_list = ? WHERE id = ?";
        jdbcTemplate.update(sql, user_hisyory, id);

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

    private static class VideoItemRowMapper implements RowMapper<VideoItem> {
        @Override
        public VideoItem mapRow(ResultSet rs, int rowNum) throws SQLException {
            VideoItem videoItem = new VideoItem(rs.getString("id"), rs.getString("name"), rs.getString("poster_path"),
                    rs.getString("video_path"));
            return videoItem;
        }
    }

}

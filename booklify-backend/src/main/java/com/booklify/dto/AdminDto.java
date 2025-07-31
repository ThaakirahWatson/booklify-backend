package com.booklify.dto;

import com.booklify.domain.enums.Permissions;

import java.time.LocalDateTime;
import java.util.List;

public class AdminDto {

    private Long id;
    private String fullName;
    private String email;
    private LocalDateTime dateJoined;
    private LocalDateTime lastLogin;
    private List<Permissions> permissions;

    // Constructors
    public AdminDto() {
    }

    public AdminDto(Long id, String fullName, String email, LocalDateTime dateJoined, LocalDateTime lastLogin, List<Permissions> permissions) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.dateJoined = dateJoined;
        this.lastLogin = lastLogin;
        this.permissions = permissions;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(LocalDateTime dateJoined) {
        this.dateJoined = dateJoined;
    }

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    public List<Permissions> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permissions> permissions) {
        this.permissions = permissions;
    }
}


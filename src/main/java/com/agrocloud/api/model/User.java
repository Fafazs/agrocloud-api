package com.agrocloud.api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String email;
    private String password;
    private boolean isAnonymous; 

    protected User() {}

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.isAnonymous = false;
    }

    public static User createAnonymous() {
        User user = new User();
        user.name = "Visitante";
        user.email = "anonimo@agrocloud.com";
        user.isAnonymous = true;
        return user;
    }

    public void updateProfile(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; } 
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public boolean isAnonymous() { return isAnonymous; }
}
package com.agrocloud.api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "fields")
public class Field {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String cropName; 

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    protected Field() {}

    public Field(String name, String cropName, User user) {
        this.name = name;
        this.cropName = cropName;
        this.user = user;
    }

    // Getters
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getCropName() { return cropName; }
    public User getUser() { return user; }
}
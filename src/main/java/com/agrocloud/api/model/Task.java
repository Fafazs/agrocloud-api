package com.agrocloud.api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tasks")
public class Task {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String title;
    private String description;
    private int priority; 
    private String status; 
    private int irrigationDurationMinutes; 

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "field_id")
    private Field field;

    protected Task() {}

    public Task(String title, String description, int priority, int irrigationDurationMinutes, Field field) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.irrigationDurationMinutes = irrigationDurationMinutes;
        this.field = field;
        this.status = "PENDENTE";
    }

    public void markAsCompleted() {
        this.status = "CONCLUIDO";
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public int getPriority() { return priority; }
    public String getStatus() { return status; }
    public int getIrrigationDurationMinutes() { return irrigationDurationMinutes; }
    public Field getField() { return field; }
}
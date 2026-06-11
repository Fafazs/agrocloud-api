package com.agrocloud.api.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "articles")
public class Article {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String title;
    private String imageUrl;
    
    @Column(columnDefinition = "TEXT")
    private String description;

    @ElementCollection
    private List<String> companionPlants; 
    private String recommendedTemperature;
    private String recommendedHumidity;
    private String soilType;
  

    protected Article() {}

    public Article(String title, String imageUrl, String description, List<String> companionPlants, 
                   String recommendedTemperature, String recommendedHumidity, String soilType) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.description = description;
        this.companionPlants = companionPlants;
        this.recommendedTemperature = recommendedTemperature;
        this.recommendedHumidity = recommendedHumidity;
        this.soilType = soilType;
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getImageUrl() { return imageUrl; }
    public String getDescription() { return description; }
    public List<String> getCompanionPlants() { return companionPlants; }
    public String getRecommendedTemperature() { return recommendedTemperature; }
    public String getRecommendedHumidity() { return recommendedHumidity; }
    public String getSoilType() { return soilType; }
    
}
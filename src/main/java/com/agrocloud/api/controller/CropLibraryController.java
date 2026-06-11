package com.agrocloud.api.controller;

import com.agrocloud.api.model.Article;
import com.agrocloud.api.service.AgroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/library/crops")
@CrossOrigin(origins = "*")
public class CropLibraryController {

    private final AgroService agroService;

    public CropLibraryController(AgroService agroService) {
        this.agroService = agroService;
    }

    @GetMapping
    public ResponseEntity<List<Article>> getCropsList() {
        return ResponseEntity.ok(agroService.getAllArticles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Article> getCropDetail(@PathVariable Long id) {
        return agroService.getArticleById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
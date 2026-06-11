package com.agrocloud.api.controller;

import com.agrocloud.api.model.User;
import com.agrocloud.api.service.AgroService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin(origins = "*")
public class HomeController {

    private final AgroService agroService;

    public HomeController(
        AgroService agroService
    ) {
        this.agroService = agroService;
    }

    @GetMapping("/summary")
    public ResponseEntity<Map<String, String>>
    getSummary(
        @RequestParam Long userId
    ) {

        User user =
            agroService
                .getUserById(userId)
                .orElseThrow();

        return ResponseEntity.ok(
            Map.of(
                "userName",
                user.getName(),

                "currentTemperature",
                "28°C",

                "weatherCondition",
                "Ensolarado"
            )
        );
    }
}
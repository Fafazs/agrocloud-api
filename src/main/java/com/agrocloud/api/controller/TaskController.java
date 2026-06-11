package com.agrocloud.api.controller;

import com.agrocloud.api.model.Task;
import com.agrocloud.api.service.AgroService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "*")
public class TaskController {

    private final AgroService agroService;

    public TaskController(AgroService agroService) {
        this.agroService = agroService;
    }

    @GetMapping("/today")
public ResponseEntity<List<Task>> getTodayTasks(
        @RequestParam Long userId
) {

    return ResponseEntity.ok(
        agroService.getTodayTasksForUser(
            userId
        )
    );
}

    @PostMapping
    public ResponseEntity<Task> createManualTask(@RequestBody Map<String, Object> body) {
        String title = (String) body.get("title");
        String description = (String) body.get("description");
        Long fieldId = Long.valueOf(body.get("fieldId").toString());
        
        Task newTask = agroService.createManualTask(title, description, fieldId);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTask);
    }
    @PatchMapping("/{taskId}/complete")
public ResponseEntity<Task> completeTask(
    @PathVariable Long taskId
) {

    Task task =
        agroService.completeTask(taskId);

    return ResponseEntity.ok(task);
}
}
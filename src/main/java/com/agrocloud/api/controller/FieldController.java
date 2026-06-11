package com.agrocloud.api.controller;

import com.agrocloud.api.model.Field;
import com.agrocloud.api.model.Task;
import com.agrocloud.api.service.AgroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/fields")
@CrossOrigin(origins = "*")
public class FieldController {

    private final AgroService agroService;

    public FieldController(AgroService agroService) {
        this.agroService = agroService;
    }

    @DeleteMapping("/{fieldId}")
public ResponseEntity<Void> deleteField(
    @PathVariable Long fieldId
) {

    agroService.deleteField(fieldId);

    return ResponseEntity.noContent().build();

}
@PostMapping
public ResponseEntity<Field> createField(
    @RequestBody Map<String, Object> body
) {

    String fieldName =
        body.get("fieldName")
            .toString();

    String cropName =
        body.get("cropName")
            .toString();

    Long userId =
        Long.valueOf(
            body.get("userId")
                .toString()
        );

    Field field =
        agroService.createField(
            fieldName,
            cropName,
            userId
        );

    return ResponseEntity.ok(
        field
    );
}

@GetMapping("/{fieldId}/tasks")
public ResponseEntity<List<Task>> getFieldTasks(
    @PathVariable Long fieldId
) {

    return ResponseEntity.ok(
        agroService.getTasksByField(
            fieldId
        )
    );

}

   @GetMapping("/status-overview")
public ResponseEntity<List<Map<String, Object>>> getStatusOverview(
        @RequestParam Long userId
) {

    List<Field> fields =
        agroService.getUserFields(userId);

    List<Map<String, Object>> overviewList =
        new ArrayList<>();

    for (Field f : fields) {

        List<Task> tasks =
            agroService.getTasksByField(
                f.getId()
            );

        long totalTasks =
            tasks.size();

        long completedTasks =
            tasks.stream()
                 .filter(t ->
                     t.getStatus()
                      .equals("CONCLUIDO")
                 )
                 .count();

        boolean isFullyCompleted =
            totalTasks > 0 &&
            totalTasks == completedTasks;

        overviewList.add(
            Map.of(
                "fieldId", f.getId(),
                "fieldName", f.getName(),
                "cropName", f.getCropName(),
                "totalTasksToday", totalTasks,
                "completedTasksToday", completedTasks,
                "isFullyCompleted", isFullyCompleted
            )
        );
    }

    return ResponseEntity.ok(
        overviewList
    );
}
}
package com.agrocloud.api.repository;
import com.agrocloud.api.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByFieldUserIdAndStatus(Long userId, String status);
    List<Task> findByFieldId(Long fieldId);
}
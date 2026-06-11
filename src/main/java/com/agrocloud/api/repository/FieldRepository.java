package com.agrocloud.api.repository;
import com.agrocloud.api.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface FieldRepository extends JpaRepository<Field, Long> {
    List<Field> findByUserId(Long userId);
}
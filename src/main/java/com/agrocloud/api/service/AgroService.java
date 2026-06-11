package com.agrocloud.api.service;

import com.agrocloud.api.dto.RegisterRequest;
import com.agrocloud.api.model.*;
import com.agrocloud.api.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class AgroService {

    private final UserRepository userRepo;
    private final FieldRepository fieldRepo;
    private final TaskRepository taskRepo;
    private final ArticleRepository articleRepo;

    public AgroService(UserRepository userRepo, FieldRepository fieldRepo, TaskRepository taskRepo, ArticleRepository articleRepo) {
        this.userRepo = userRepo;
        this.fieldRepo = fieldRepo;
        this.taskRepo = taskRepo;
        this.articleRepo = articleRepo;
    }


    @Transactional
    public User registerUser(RegisterRequest request) {
        User user = new User(request.getName(), request.getEmail(), request.getPassword());
        User savedUser = userRepo.save(user);

        String defaultCropName = "Cultivo Especial";
        if (request.getCropId() == 2) defaultCropName = "Tomate Cereja";
        else if (request.getCropId() == 1) defaultCropName = "Alface Crespa";

        Field defaultField = fieldRepo.save(new Field("Meu Primeiro Canteiro", defaultCropName, savedUser));

        taskRepo.save(new Task("Irrigação da Manhã", "Automatizada pelo motor hídrico", 1, 15, defaultField));
        taskRepo.save(new Task("Adubação Orgânica Inicial", "Missão do ciclo de vida", 2, 0, defaultField));

        return savedUser;
    }

    public Optional<User> loginUser(String email, String password) {
        return userRepo.findByEmail(email)
                .filter(user -> user.getPassword().equals(password)); 
    }

    @Transactional
    public User createAnonymousUser() {
        User anonymous = User.createAnonymous();
        return userRepo.save(anonymous);
    }

   @Transactional
public void deleteField(Long fieldId) {

    List<Task> tasks =
        taskRepo.findByFieldId(fieldId);

    taskRepo.deleteAll(tasks);

    fieldRepo.deleteById(fieldId);

}

@Transactional
public Field createField(
    String fieldName,
    String cropName,
    Long userId
) {

    User user =
        userRepo.findById(userId)
                .orElseThrow();

    Field field =
        new Field(
            fieldName,
            cropName,
            user
        );

    Field savedField =
        fieldRepo.save(field);

    taskRepo.save(
        new Task(
            "Irrigação da Manhã",
            "Automatizada pelo motor hídrico",
            1,
            15,
            savedField
        )
    );

    taskRepo.save(
        new Task(
            "Adubação Orgânica Inicial",
            "Missão do ciclo de vida",
            2,
            0,
            savedField
        )
    );

    return savedField;
}

@Transactional
public Task completeTask(
    Long taskId
) {

    Task task =
        taskRepo.findById(taskId)
                .orElseThrow();

    task.markAsCompleted();

    return taskRepo.save(task);
}
    public List<Task> getTodayTasksForUser(Long userId) {
        return taskRepo.findByFieldUserIdAndStatus(userId, "PENDENTE");
    }

    @Transactional
    public Task createManualTask(String title, String description, Long fieldId) {
        Field field = fieldRepo.findById(fieldId).orElseThrow();
        return taskRepo.save(new Task(title, description, 3, 0, field));
    }


    public List<Field> getUserFields(Long userId) {
        return fieldRepo.findByUserId(userId);
    }

    public List<Task> getTasksByField(Long fieldId) {
        return taskRepo.findByFieldId(fieldId);
    }


    @Transactional
    public User updateProfile(Long userId, String name, String email) {
        User user = userRepo.findById(userId).orElseThrow();
        user.updateProfile(name, email);
        return userRepo.save(user);
    }

    @Transactional
    public void deleteUserAccount(Long userId) {
        userRepo.deleteById(userId); 
    }

    public Optional<User> getUserById(
    Long userId
) {
    return userRepo.findById(userId);
}


    public List<Article> getAllArticles() {
        return articleRepo.findAll();
    }

    public Optional<Article> getArticleById(Long id) {
        return articleRepo.findById(id);
    }
}
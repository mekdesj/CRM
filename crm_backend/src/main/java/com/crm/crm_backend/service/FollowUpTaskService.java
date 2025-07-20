package com.crm.crm_backend.service;

import com.crm.crm_backend.entity.FollowUpTask;
import com.crm.crm_backend.entity.User;
import com.crm.crm_backend.repository.FollowUpTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class FollowUpTaskService {

    @Autowired
    private FollowUpTaskRepository repository;

    public FollowUpTask createTask(FollowUpTask task) {
        task.setCreatedAt(LocalDateTime.now());
        return repository.save(task);
    }

    public List<FollowUpTask> getTasksForUser(User user) {
        return repository.findByAssignedTo(user);
    }

    public Optional<FollowUpTask> getTask(Long id) {
        return repository.findById(id);
    }

    public void deleteTask(Long id) {
        repository.deleteById(id);
    }

    public FollowUpTask updateTask(FollowUpTask updatedTask) {
        // You can add null checks or validations here
        return repository.save(updatedTask);
    }
}


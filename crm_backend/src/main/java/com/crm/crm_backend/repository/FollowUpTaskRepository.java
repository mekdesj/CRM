package com.crm.crm_backend.repository;

import com.crm.crm_backend.entity.FollowUpTask;
import com.crm.crm_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FollowUpTaskRepository extends JpaRepository<FollowUpTask, Long> {
    List<FollowUpTask> findByAssignedTo(User user);
}

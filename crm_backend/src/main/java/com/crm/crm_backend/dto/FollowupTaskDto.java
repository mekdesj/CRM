package com.crm.crm_backend.dto;

public class FollowupTaskDto {
    private String title;
    private String description;
    private String createdAt; // ISO format string like "2025-06-18T10:00:00"
    private Long assignedToId;

    // Getters and Setters
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Long getAssignedToId() {
        return assignedToId;
    }
    public void setAssignedToId(Long assignedToId) {
        this.assignedToId = assignedToId;
    }
}

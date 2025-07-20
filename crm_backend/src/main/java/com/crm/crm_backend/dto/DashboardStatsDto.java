package com.crm.crm_backend.dto;

import java.util.Map;

public class DashboardStatsDto {
    private long totalLeads;
    private long totalCustomers;
    private Map<String, Integer> leadsByStatus;
    private long totalTasks;

    // ✅ Constructor
    public DashboardStatsDto(long totalLeads, long totalCustomers, Map<String, Integer> leadsByStatus, long totalTasks) {
        this.totalLeads = totalLeads;
        this.totalCustomers = totalCustomers;
        this.leadsByStatus = leadsByStatus;
        this.totalTasks = totalTasks;
    }

    // ✅ Getters
    public long getTotalLeads() {
        return totalLeads;
    }

    public long getTotalCustomers() {
        return totalCustomers;
    }

    public Map<String, Integer> getLeadsByStatus() {
        return leadsByStatus;
    }

    public long getTotalTasks() {
        return totalTasks;
    }

    // ✅ Setters (optional but safe to include)
    public void setTotalLeads(long totalLeads) {
        this.totalLeads = totalLeads;
    }

    public void setTotalCustomers(long totalCustomers) {
        this.totalCustomers = totalCustomers;
    }

    public void setLeadsByStatus(Map<String, Integer> leadsByStatus) {
        this.leadsByStatus = leadsByStatus;
    }

    public void setTotalTasks(long totalTasks) {
        this.totalTasks = totalTasks;
    }
}

package com.crm.crm_backend.service;

import com.crm.crm_backend.dto.DashboardStatsDto;
import com.crm.crm_backend.repository.CustomerRepository;
import com.crm.crm_backend.repository.LeadRepository;
import com.crm.crm_backend .repository.FollowUpTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DashboardService {

    @Autowired
    private LeadRepository leadRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private FollowUpTaskRepository taskRepository;

    public DashboardStatsDto getDashboardStats() {
        long totalLeads = leadRepository.count();
        long totalCustomers = customerRepository.count();
        long totalTasks = taskRepository.count();

        // ✅ Fix is here
        List<Object[]> rawLeadsByStatus = leadRepository.countLeadsByStatus();

        Map<String, Integer> leadsByStatusMap = new HashMap<>();
        for (Object[] row : rawLeadsByStatus) {
            String status = row[0] != null ? row[0].toString() : "UNKNOWN";
            Integer count = row[1] != null ? ((Number) row[1]).intValue() : 0;
            leadsByStatusMap.put(status, count);
        }

        return new DashboardStatsDto(totalLeads, totalCustomers, leadsByStatusMap, totalTasks);
    }
}

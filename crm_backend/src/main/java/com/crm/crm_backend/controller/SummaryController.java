package com.crm.crm_backend.controller;

import com.crm.crm_backend.repository.CustomerRepository;
import com.crm.crm_backend.repository.FollowUpTaskRepository;
import com.crm.crm_backend.repository.LeadRepository;
import com.crm.crm_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
public class SummaryController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private LeadRepository leadRepository;
    @Autowired
    private FollowUpTaskRepository followUpRepository;

    @GetMapping("/summary")
    public Map<String, Long> getSummary() {
        Map<String, Long> counts = new HashMap<>();
        counts.put("usersCount", userRepository.count());
        counts.put("customersCount", customerRepository.count());
        counts.put("leadsCount", leadRepository.count());
        counts.put("followUpsCount", followUpRepository.count());
        return counts;
    }
}

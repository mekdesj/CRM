package com.crm.crm_backend.service;

import com.crm.crm_backend.dto.LeadDto;
import com.crm.crm_backend.entity.Lead;
import com.crm.crm_backend.entity.User;
import com.crm.crm_backend.repository.LeadRepository;
import com.crm.crm_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LeadService {

    @Autowired
    private LeadRepository leadRepository;

    @Autowired
    private UserRepository userRepository;


    public Lead createLead(LeadDto dto, String username) {
        User creator = userRepository.findByUsername(username).orElseThrow();

        if (dto.getAssignedToId() == null) {
            throw new IllegalArgumentException("assignedToId must be provided.");
        }

        User assignedTo = userRepository.findById(dto.getAssignedToId())
                .orElseThrow(() -> new IllegalArgumentException("Assigned user not found for ID: " + dto.getAssignedToId()));

        Lead lead = new Lead();
        lead.setName(dto.getName());
        lead.setEmail(dto.getEmail());
        lead.setPhone(dto.getPhone());
        lead.setStatus(dto.getStatus());
        lead.setCreatedAt(LocalDateTime.now());
        lead.setCreatedBy(creator);
        lead.setAssignedTo(assignedTo);



        return leadRepository.save(lead);
    }


    public List<Lead> getLeads(String username) {
        User user = userRepository.findByUsername(username).orElseThrow();

        if (user.getRole().equals("ADMIN")) {
            return leadRepository.findAll();
        } else {
            return leadRepository.findByAssignedTo(user);
        }
    }


    public Lead getLead(Long id, String username) {
        Lead lead = leadRepository.findById(id).orElseThrow();
        User user = userRepository.findByUsername(username).orElseThrow();

        if (user.getRole().equals("ADMIN") || lead.getAssignedTo().getId().equals(user.getId())) {
            return lead;
        }

        throw new AccessDeniedException("Not your lead, not your business ");
    }


    public Lead updateLead(Long id, LeadDto dto, String username) {
        Lead lead = leadRepository.findById(id).orElseThrow();
        User user = userRepository.findByUsername(username).orElseThrow();

        boolean isAdmin = user.getRole().equals("ADMIN");
        boolean isAssigned = lead.getAssignedTo().getId().equals(user.getId());

        if (!isAdmin && !isAssigned) {
            throw new AccessDeniedException("Nope. You can't update this lead 👎");
        }

        lead.setName(dto.getName());
        lead.setEmail(dto.getEmail());
        lead.setPhone(dto.getPhone());
        lead.setStatus(dto.getStatus());

        return leadRepository.save(lead);
    }


    public void deleteLead(Long id, String username) {
        Lead lead = leadRepository.findById(id).orElseThrow();
        User user = userRepository.findByUsername(username).orElseThrow();

        if (user.getRole().equals("ADMIN") || lead.getAssignedTo().getId().equals(user.getId())) {
            leadRepository.deleteById(id);
        } else {
            throw new AccessDeniedException("You can't delete what you don't own 👻");
        }
    }
}

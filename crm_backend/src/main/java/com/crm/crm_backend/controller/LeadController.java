package com.crm.crm_backend.controller;

import com.crm.crm_backend.dto.LeadDto;
import com.crm.crm_backend.entity.Lead;
import com.crm.crm_backend.service.LeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leads")
public class LeadController {

    @Autowired
    private LeadService leadService;

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PostMapping
    public ResponseEntity<Lead> createLead(@RequestBody LeadDto dto, Authentication auth) {
        Lead created = leadService.createLead(dto, auth.getName());
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping
    public ResponseEntity<List<Lead>> getLeads(Authentication auth) {
        List<Lead> leads = leadService.getLeads(auth.getName());
        return ResponseEntity.ok(leads);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/{id}")
    public ResponseEntity<Lead> getLead(@PathVariable Long id, Authentication auth) {
        Lead lead = leadService.getLead(id, auth.getName());
        return ResponseEntity.ok(lead);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PutMapping("/{id}")
    public ResponseEntity<Lead> updateLead(@PathVariable Long id, @RequestBody LeadDto dto, Authentication auth) {
        Lead updated = leadService.updateLead(id, dto, auth.getName());
        return ResponseEntity.ok(updated);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLead(@PathVariable Long id, Authentication auth) {
        leadService.deleteLead(id, auth.getName());
        return ResponseEntity.noContent().build();
    }
}

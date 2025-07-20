package com.crm.crm_backend.repository;

import com.crm.crm_backend.entity.Lead;
import com.crm.crm_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LeadRepository extends JpaRepository<Lead, Long> {

    // ✅ Counts number of leads grouped by their status (e.g., NEW, CONTACTED, etc.)
    @Query("SELECT l.status, COUNT(l) FROM Lead l GROUP BY l.status")
    List<Object[]> countLeadsByStatus();

    // ✅ Fetch leads assigned to a specific user (agent)
    List<Lead> findByAssignedTo(User user);
}


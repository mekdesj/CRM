package com.crm.crm_backend.repository;

import com.crm.crm_backend.entity.Customer;
import com.crm.crm_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByCreatedBy(User user);
}

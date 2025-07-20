package com.crm.crm_backend.repository;

import com.crm.crm_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<com.crm.crm_backend.entity.User, Long> {
    Optional<User> findByUsername(String username);

}

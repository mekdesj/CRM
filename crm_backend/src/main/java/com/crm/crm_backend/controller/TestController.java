package com.crm.crm_backend.controller;

import com.crm.crm_backend.entity.User;
import com.crm.crm_backend.repository.UserRepository; // ← Import this!
import com.crm.crm_backend.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class TestController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }



    @RestController
    @RequestMapping("/admin")
    class AdminController {

        @GetMapping("/dashboard")
        public ResponseEntity<String> getAdminData() {
            return ResponseEntity.ok("Welcome Admin 🚀");
        }
    }

    @RestController
    @RequestMapping("/manager")
    class ManagerController {

        @GetMapping("/dashboard")
        public ResponseEntity<String> getManagerData() {
            return ResponseEntity.ok("Welcome Manager 🛠️");
        }
    }
}
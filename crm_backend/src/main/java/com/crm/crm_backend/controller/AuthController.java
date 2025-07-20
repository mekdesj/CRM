package com.crm.crm_backend.controller;

import com.crm.crm_backend.dto.AuthRequest;
import com.crm.crm_backend.dto.AuthResponse;
import com.crm.crm_backend.entity.User;
import com.crm.crm_backend.repository.UserRepository;
import com.crm.crm_backend.security.JwtUtil;
import com.crm.crm_backend.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.crm.crm_backend.dto.RegisterRequest;


@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder encoder;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Username already exists");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(encoder.encode(request.getPassword()));

        // Validate and set role
        String role = request.getRole();
        if (role == null || role.isEmpty()) {
            user.setRole("USER"); // default
        } else if (role.equals("ADMIN") || role.equals("USER") || role.equals("MANAGER")) {
            user.setRole(role);
        } else {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Invalid role. Allowed: ADMIN, USER, MANAGER");
        }

        userRepository.save(user);

        return ResponseEntity.ok("User registered successfully");
    }




    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody AuthRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );

            UserDetails userDetails = userService.loadUserByUsername(request.getUsername());

            String token = jwtUtil.generateToken(userDetails);

            return ResponseEntity.ok(new AuthResponse(token));
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(403).body("Login failed: " + ex.getMessage());
        }
    }

}





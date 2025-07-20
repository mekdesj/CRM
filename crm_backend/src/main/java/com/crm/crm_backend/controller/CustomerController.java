package com.crm.crm_backend.controller;

import com.crm.crm_backend.dto.CustomerDto;
import com.crm.crm_backend.entity.Customer;
import com.crm.crm_backend.service.CustomerService;
import org.springframework.security.core.Authentication; // ✅ Correct import
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping
    public List<Customer> getCustomers(Authentication auth) {
        return customerService.getCustomers(auth.getName());
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PostMapping
    public Customer addCustomer(@RequestBody CustomerDto dto, Authentication auth) {
        return customerService.createCustomer(dto, auth.getName());
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PutMapping("/{id}")
    public Customer updateCustomer(@PathVariable Long id, @RequestBody CustomerDto dto, Authentication auth) {
        return customerService.updateCustomer(id, dto, auth.getName());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable Long id, Authentication auth) {
        customerService.deleteCustomer(id, auth.getName()); // ✅ Pass both id and username
    }
}

package com.crm.crm_backend.service;

import com.crm.crm_backend.dto.CustomerDto;
import com.crm.crm_backend.entity.Customer;
import com.crm.crm_backend.entity.User;
import com.crm.crm_backend.repository.CustomerRepository;
import com.crm.crm_backend.repository.UserRepository;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private UserRepository userRepository;

    // 🔍 Get all customers
    public List<Customer> getCustomers(String username) {
        User user = userRepository.findByUsername(username).orElseThrow();

        if (user.getRole().equals("ADMIN")) {
            return customerRepository.findAll();
        } else {
            return customerRepository.findByCreatedBy(user);
        }
    }
@Transactional
    // ➕ Create new customer
    public Customer createCustomer(CustomerDto dto, String username) {
        User user = userRepository.findByUsername(username).orElseThrow();

        Customer customer = new Customer();
        customer.setName(dto.getName());
        customer.setEmail(dto.getEmail());
        customer.setPhone(dto.getPhone());
        customer.setAddress(dto.getAddress());
        customer.setCreatedAt(LocalDateTime.now());
        customer.setCreatedBy(user);

        return customerRepository.save(customer);
    }

    // 🛠️ Update customer (Admin or owner only)
    public Customer updateCustomer(Long id, CustomerDto dto, String username) {
        Customer customer = customerRepository.findById(id).orElseThrow();
        User user = userRepository.findByUsername(username).orElseThrow();

        boolean isAdmin = user.getRole().equals("ADMIN");
        boolean isOwner = customer.getCreatedBy().getId().equals(user.getId());

        if (!isAdmin && !isOwner) {
            throw new AccessDeniedException("You don't have permission to edit this customer 🚫");
        }

        customer.setName(dto.getName());
        customer.setEmail(dto.getEmail());
        customer.setPhone(dto.getPhone());
        customer.setAddress(dto.getAddress());

        return customerRepository.save(customer);
    }

    // ❌ Delete (Admin only)
    public void deleteCustomer(Long id, String username) {
        User user = userRepository.findByUsername(username).orElseThrow();
        Customer customer = customerRepository.findById(id).orElseThrow();

        if (!user.getRole().equals("ADMIN")) {
            throw new AccessDeniedException("Only admins can delete customers ❌");
        }

        customerRepository.delete(customer);
    }

}

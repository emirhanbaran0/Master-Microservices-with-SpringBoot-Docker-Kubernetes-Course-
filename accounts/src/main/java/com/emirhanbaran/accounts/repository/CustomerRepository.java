package com.emirhanbaran.accounts.repository;

import com.emirhanbaran.accounts.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
}

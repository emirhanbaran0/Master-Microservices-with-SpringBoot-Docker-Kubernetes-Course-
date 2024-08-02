package com.emirhanbaran.accounts.repository;

import com.emirhanbaran.accounts.entity.Account;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account,Long> {
    Optional<Account> findByCustomerId(Long customerId);

    @Modifying //this annotation let Spring know that this method modifying the data
    @Transactional
    void deleteByCustomerId(Long customerId);

}

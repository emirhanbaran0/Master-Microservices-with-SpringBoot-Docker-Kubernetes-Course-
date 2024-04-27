package com.emirhanbaran.accounts.repository;

import com.emirhanbaran.accounts.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Long> {
}

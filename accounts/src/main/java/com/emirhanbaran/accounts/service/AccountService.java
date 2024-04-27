package com.emirhanbaran.accounts.service;

import com.emirhanbaran.accounts.dto.CustomerDto;
import com.emirhanbaran.accounts.entity.Account;
import com.emirhanbaran.accounts.repository.AccountRepository;
import com.emirhanbaran.accounts.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;

    /**
     *
     * @param customerDto -CustomerDto Object
     */
    void createAccount(CustomerDto customerDto) {

    }
}

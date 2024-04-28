package com.emirhanbaran.accounts.service;

import com.emirhanbaran.accounts.constants.AccountConstants;
import com.emirhanbaran.accounts.dto.AccountsDto;
import com.emirhanbaran.accounts.dto.CustomerDto;
import com.emirhanbaran.accounts.entity.Account;
import com.emirhanbaran.accounts.entity.Customer;
import com.emirhanbaran.accounts.exception.CustomerAlreadyExistException;
import com.emirhanbaran.accounts.exception.ResourceNotFoundException;
import com.emirhanbaran.accounts.mapper.AccountsMapper;
import com.emirhanbaran.accounts.mapper.CustomerMapper;
import com.emirhanbaran.accounts.repository.AccountRepository;
import com.emirhanbaran.accounts.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;

    /**
     *
     * @param customerDto -CustomerDto Object
     */
    public void createAccount(CustomerDto customerDto) {
        Customer customer = CustomerMapper.mapToCustomer(customerDto,new Customer());
        if(customerRepository.findByMobileNumber(customer.getMobileNumber()).isPresent()){
            throw new CustomerAlreadyExistException("Customer already registered with given mobile number: "+customerDto.getMobileNumber());
        }
        customer.setCreatedAt(LocalDateTime.now());
        customer.setCreatedBy("Anonymous");
        customer.setUpdatedAt(LocalDateTime.now());
        customer.setUpdatedBy("Anonymous");
        Customer savedCustomer=customerRepository.save(customer);
        accountRepository.save(createNewAccount(savedCustomer));
    }

    private Account createNewAccount(Customer customer){
        Account account = new Account();
        account.setCustomerId(customer.getCustomerId());
        long randomAccNumber=10000000000L + new Random().nextInt(900000000);
        account.setAccountNumber(randomAccNumber);
        account.setAccountType(AccountConstants.SAVINGS);
        account.setBranchAddress(AccountConstants.ADDRESS);
        account.setCreatedAt(LocalDateTime.now());
        account.setCreatedBy("Anonymous");
        account.setUpdatedAt(LocalDateTime.now());
        account.setUpdatedBy("Anonymous");
        return account;
    }

    public CustomerDto fetchAccount(String mobileNumber){
        Customer customer=customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()->{throw new ResourceNotFoundException("Customer","mobileNumber",mobileNumber);}
        );
        Account account=accountRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                ()->{throw new ResourceNotFoundException("Account", "customerId",customer.getCustomerId().toString());}
        );
        CustomerDto customerDto=CustomerMapper.mapToCustomerDto(customer,new CustomerDto());
        AccountsDto accountsDto=AccountsMapper.mapToAccountsDto(account,new AccountsDto());
        customerDto.setAccountsDto(accountsDto);
        return customerDto;
    }

    public boolean updateAccount(CustomerDto customerDto){
        boolean isUpdated=false;
        AccountsDto accountsDto=customerDto.getAccountsDto();
        if(accountsDto!=null){
            Account account=accountRepository.findById(accountsDto.getAccountNumber()).orElseThrow(
                    ()->{throw new ResourceNotFoundException("Account","accountNumber",accountsDto.getAccountNumber().toString());}
            );
            AccountsMapper.mapToAccount(accountsDto,account);
            account=accountRepository.save(account);

            Long customerId=account.getCustomerId();
            Customer customer=customerRepository.findById(customerId).orElseThrow(
                    ()->{throw new ResourceNotFoundException("Customer","customerId",customerId.toString());}
            );
            CustomerMapper.mapToCustomer(customerDto, customer);
            customer.setUpdatedAt(LocalDateTime.now());
            customer.setUpdatedBy("Anonymous");
            customerRepository.save(customer);
            isUpdated = true;
        }
        return isUpdated;
    }

    public boolean deleteAccount(String mobileNumber){
       Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()-> new ResourceNotFoundException("Customer","mobileNumber",mobileNumber)
        );
       accountRepository.deleteByCustomerId(customer.getCustomerId());
       customerRepository.deleteById(customer.getCustomerId());
       return true;
    }
}

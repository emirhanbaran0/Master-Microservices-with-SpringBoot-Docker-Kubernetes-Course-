package com.emirhanbaran.accounts.service;

import com.emirhanbaran.accounts.dto.AccountsDto;
import com.emirhanbaran.accounts.dto.CardsDto;
import com.emirhanbaran.accounts.dto.CustomerDetailsDto;
import com.emirhanbaran.accounts.dto.LoansDto;
import com.emirhanbaran.accounts.entity.Account;
import com.emirhanbaran.accounts.entity.Customer;
import com.emirhanbaran.accounts.exception.ResourceNotFoundException;
import com.emirhanbaran.accounts.mapper.AccountsMapper;
import com.emirhanbaran.accounts.mapper.CustomerMapper;
import com.emirhanbaran.accounts.repository.AccountRepository;
import com.emirhanbaran.accounts.repository.CustomerRepository;
import com.emirhanbaran.accounts.service.client.CardsFeignClient;
import com.emirhanbaran.accounts.service.client.LoansFeignClient;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static com.emirhanbaran.accounts.mapper.CustomerMapper.mapToCustomerDetailsDto;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final CardsFeignClient cardsFeignClient;
    private final LoansFeignClient loansFeignClient;


    /**
     *
     * @param mobileNumber - Input Mobile Number
     * @return Customer Details based on a given mobileNumber
     */
    public CustomerDetailsDto fetchCustomerDetails(String mobileNumber,String correlationId) {
        Customer customer=customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()->{throw new ResourceNotFoundException("Customer","mobileNumber",mobileNumber);}
        );
        Account account=accountRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                ()->{throw new ResourceNotFoundException("Account", "customerId",customer.getCustomerId().toString());}
        );
        CustomerDetailsDto customerDetailsDto=CustomerMapper.mapToCustomerDetailsDto(customer,new CustomerDetailsDto());
        customerDetailsDto.setAccountsDto(AccountsMapper.mapToAccountsDto(account,new AccountsDto()));
        ResponseEntity<CardsDto> cardsResponse = cardsFeignClient.fetchCardDetails(correlationId,mobileNumber);
        if (cardsResponse.getStatusCode() == HttpStatus.OK) {
            customerDetailsDto.setCardsDto(cardsResponse.getBody());
        }
        ResponseEntity<LoansDto> loansResponse = loansFeignClient.fetchLoanDetails(correlationId,mobileNumber);
        if (loansResponse.getStatusCode() == HttpStatus.OK) {
            customerDetailsDto.setLoansDto(loansResponse.getBody());
        }
            return customerDetailsDto;
    }
}

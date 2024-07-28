package com.emirhanbaran.accounts.functions;

import com.emirhanbaran.accounts.dto.AccountsMsgDto;
import com.emirhanbaran.accounts.service.AccountService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.logging.Logger;

@Configuration
public class AccountsFunctions {

    private static final Logger logger = Logger.getLogger(AccountsFunctions.class.getName());

    @Bean
    public Consumer<Long> updateCommunication(AccountService accountService){
        return accountNumber ->{
            logger.info("Updating communication status for the account number: " + accountNumber);
            accountService.updateCommunicationStatus(accountNumber);
        };
    }
}

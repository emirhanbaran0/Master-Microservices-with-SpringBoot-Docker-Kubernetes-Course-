package com.emirhanbaran.accounts.mapper;

import com.emirhanbaran.accounts.dto.AccountsDto;
import com.emirhanbaran.accounts.entity.Account;

public class AccountsMapper {

    public static AccountsDto mapToAccountsDto(Account account,AccountsDto accountsDto) {
        accountsDto.setAccountNumber(accountsDto.getAccountNumber());
        accountsDto.setAccountType(accountsDto.getAccountType());
        accountsDto.setBranchAddress(accountsDto.getBranchAddress());
        return accountsDto;
    }

    public static Account mapToAccount(AccountsDto accountsDto, Account account) {
        account.setAccountNumber(accountsDto.getAccountNumber());
        account.setAccountType(accountsDto.getAccountType());
        account.setBranchAddress(accountsDto.getBranchAddress());
        return account;
    }
}

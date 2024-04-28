package com.emirhanbaran.accounts.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AccountsDto {

    @Pattern(regexp = "($|[0-9]{10})", message = "Mobile number must be 10 digits")
    private Long accountNumber;
    @NotEmpty(message = "Account Type cannot be null or Empty")
    private String accountType;
    @NotEmpty(message = "Branch Address cannot be null or Empty")
    private String branchAddress;
}

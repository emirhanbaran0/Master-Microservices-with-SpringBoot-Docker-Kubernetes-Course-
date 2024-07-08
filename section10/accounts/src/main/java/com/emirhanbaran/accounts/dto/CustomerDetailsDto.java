package com.emirhanbaran.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

@Schema(name = "CustomerDetails",
        description = "Schema to hold Customer Account, Cards and Loans information"
)
@Data
public class CustomerDetailsDto {

    @NotEmpty(message = "Name cannot be null or Empty")
    @Size(min = 5, max = 30, message = "The length of name should be between 5 and 30")
    @Schema(
            description = "Name of the customer" , example = "Emirhan"
    )
    private String name;
    @Email(message = "Email should be valid value")
    @Schema(
            description = "Email of the customer" , example = "abc@def.xyz"
    )
    private String email;
    @Pattern(regexp = "($|[0-9]{10})", message = "Mobile number must be 10 digits")
    @Schema(
            description = "Mobile number of the customer" , example = "5312345321"
    )
    private String mobileNumber;
    @Schema(
            description = "Account Details of the customer"
    )
    AccountsDto accountsDto;

    @Schema(
            description = "Cards info of the customer"
    )
    CardsDto cardsDto;

    @Schema(
            description = "Loans info of the customer"
    )
    LoansDto loansDto;



}

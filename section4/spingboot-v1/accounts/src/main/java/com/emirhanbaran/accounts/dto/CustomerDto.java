package com.emirhanbaran.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
        name = "Customer",
        description = "Schema to holdd Customer and Account Information"
)
public class CustomerDto {

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
}

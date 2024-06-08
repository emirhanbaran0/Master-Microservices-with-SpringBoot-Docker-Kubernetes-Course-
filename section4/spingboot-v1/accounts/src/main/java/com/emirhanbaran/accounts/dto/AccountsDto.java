package com.emirhanbaran.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(
        description = "Schema to hold Account Information"
)
public class AccountsDto {

    @Pattern(regexp = "($|[0-9]{10})", message = "Mobile number must be 10 digits")
    @Schema(
            description = "Account Number of EazyyBank account"
    )
    private Long accountNumber;
    @NotEmpty(message = "Account Type cannot be null or Empty")
    @Schema(
            description = "Account Type of EazyyBank account"
    )
    private String accountType;
    @NotEmpty(message = "Branch Address cannot be null or Empty")
    @Schema(
            description = "Branch address of EazyyBank account"
    )
    private String branchAddress;
}

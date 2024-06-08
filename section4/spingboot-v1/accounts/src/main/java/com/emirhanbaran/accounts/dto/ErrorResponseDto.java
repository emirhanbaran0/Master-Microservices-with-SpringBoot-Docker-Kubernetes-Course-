package com.emirhanbaran.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.Instant;

@Data
@AllArgsConstructor
@Schema(
        name = "Response",
        description = "Schema to hold error response information"
)
public class ErrorResponseDto {

    @Schema(
            description = "Api path of error"
    )
    private String apiPath;
    @Schema(
            description = "Status code in the error"
    )
    private HttpStatus errorCode;
    @Schema(
            description = "Status message in the response"
    )
    private String errorMessage;
    @Schema(
            description = "Error time imply when the error occurred "
    )
    private Instant errorTime;
}

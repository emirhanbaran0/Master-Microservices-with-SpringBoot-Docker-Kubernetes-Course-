 package com.emirhanbaran.accounts.controller;


import com.emirhanbaran.accounts.dto.CustomerDetailsDto;
import com.emirhanbaran.accounts.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api",produces = {MediaType.APPLICATION_JSON_VALUE})
@RequiredArgsConstructor
@Validated
@Tag(
        name = "CRUD REST APIs for Customer in EazyBank",
        description = "CRUD REST APIs in EazyBank to CREATE,UPDATE,FETCH AND DELETE customer details"
)
public class CustomerController {

    private static final Logger logger= LoggerFactory.getLogger(CustomerController.class);
    private final CustomerService customerService;


    @Operation(summary = "Fetch Customer Details REST API"
            ,description = "REST API to fetch CustomerDetails based on mobile number")
    @ApiResponses( {
            @ApiResponse( responseCode = "200", description = "HTTP Status OK"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @GetMapping("/fetchCustomerDetails")
    public ResponseEntity<CustomerDetailsDto> fetchCustomerDetails(@RequestHeader("eazybank-correlation-id") String correlationId,
                                                                   @RequestParam
                                                                       @Pattern(regexp = "($|[0-9]{10})",
                                                                               message = "Mobile number must be 10 digits") String mobileNumber) {
            logger.debug("Customer - eazyBank Correlation Id: " + correlationId);
            CustomerDetailsDto customerDetailsDto=customerService.fetchCustomerDetails(mobileNumber,correlationId);
            return ResponseEntity.status(HttpStatus.OK).body(customerDetailsDto);
    }
}

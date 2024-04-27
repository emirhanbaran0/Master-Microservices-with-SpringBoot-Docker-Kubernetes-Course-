package com.emirhanbaran.accounts.controller;

import com.emirhanbaran.accounts.constants.AccountConstants;
import com.emirhanbaran.accounts.dto.CustomerDto;
import com.emirhanbaran.accounts.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;

@RestController
@RequestMapping(path = "/api",produces = {MediaType.APPLICATION_JSON_VALUE})
public class AccountsController {

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@RequestBody CustomerDto customerDto) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountConstants.STATUS_201,AccountConstants.MESSAGE_201));
    }
}

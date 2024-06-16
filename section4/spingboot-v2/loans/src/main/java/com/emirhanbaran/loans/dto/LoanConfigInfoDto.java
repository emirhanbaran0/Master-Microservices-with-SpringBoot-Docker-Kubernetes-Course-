package com.emirhanbaran.loans.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

@ConfigurationProperties(prefix = "loan")
@Getter
@Setter
public class LoanConfigInfoDto {

    String message;
    Map<String,String> contactDetails;
    List<String> onCallSupport;
}

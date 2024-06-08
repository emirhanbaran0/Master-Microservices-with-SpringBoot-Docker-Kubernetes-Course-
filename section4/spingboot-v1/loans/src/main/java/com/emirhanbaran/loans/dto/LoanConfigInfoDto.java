package com.emirhanbaran.loans.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

@ConfigurationProperties(prefix = "loan")
public record LoanConfigInfoDto(String message, Map<String,String> contactDetails, List<String> onCallSupport) {
}

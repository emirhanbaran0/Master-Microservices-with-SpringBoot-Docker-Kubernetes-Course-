package com.emirhanbaran.cards.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

@ConfigurationProperties(prefix = "card")
public record CardConfigInfoDto(String message, Map<String, String> contactDetails, List<String> onCallSupport) {
}

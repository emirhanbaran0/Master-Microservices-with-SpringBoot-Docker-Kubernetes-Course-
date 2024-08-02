package com.emirhanbaran.gatewayserver.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;



@Order(1)
@Component
public class RequestTraceFilter implements GlobalFilter {

    private static final Logger logger = LoggerFactory.getLogger(RequestTraceFilter.class);

    private final FilterUtility filterUtility;

    public RequestTraceFilter(FilterUtility filterUtility) {
        this.filterUtility = filterUtility;
    }


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        HttpHeaders requestHeaders=exchange.getRequest().getHeaders();
        if(isCorrelationIdPresent(requestHeaders)){
            logger.debug("eazyBank corelation-id found in RequestTraceFilter: " +filterUtility.getCorrelationId(requestHeaders));
        }else {
            String corelationId = generateCorrelationId(requestHeaders);
            exchange=filterUtility.setCorrelationId(exchange,corelationId);
            logger.debug("eazyBank corelation-id generated in RequestTraceFilter: "+corelationId);
        }
        return chain.filter(exchange);
    }

    private String generateCorrelationId(HttpHeaders requestHeaders) {
        return java.util.UUID.randomUUID().toString();
    }

    private boolean isCorrelationIdPresent(HttpHeaders requestHeaders) {
        return  filterUtility.getCorrelationId(requestHeaders)!=null;
    }


}

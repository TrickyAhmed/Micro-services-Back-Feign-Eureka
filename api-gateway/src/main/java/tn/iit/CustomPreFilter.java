package tn.iit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.http.server.reactive.ServerHttpRequest;
import reactor.core.publisher.Mono;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;


@Component
public class CustomPreFilter implements GlobalFilter {

    private static final Logger logger = LoggerFactory.getLogger(CustomPreFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // Get the request object
        ServerHttpRequest request = exchange.getRequest();

        // Log the Authorization header (if available)
        String authorizationHeader = request.getHeaders().getFirst("Authorization");

        // Check if the Authorization header is present and starts with "Bearer"


        // Log the valid Authorization header
        logger.info("Authorization header: " + authorizationHeader);

        // Continue with the filter chain (pass the request to the next filter or service)
        return chain.filter(exchange);
    }
}

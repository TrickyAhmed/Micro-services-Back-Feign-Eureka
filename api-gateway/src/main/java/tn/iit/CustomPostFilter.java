package tn.iit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;

@Component
public class CustomPostFilter implements GlobalFilter {

    private static final Logger logger = LoggerFactory.getLogger(CustomPostFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // Pre-processing logic (optional)
        logger.info("Pre-processing logic here...");

        // Continue with the request and pass it through the filter chain
        return chain.filter(exchange)
                .then(Mono.defer(() -> {
                    // Post-processing logic after the service execution
                    logger.info("Service has responded, executing post-processing logic.");

                    // Example: Log the response status code
                    int statusCode = exchange.getResponse().getStatusCode() != null
                            ? exchange.getResponse().getStatusCode().value()
                            : 0;
                    logger.info("Response Status Code: " + statusCode);

                    // Return immediately without delay
                    return Mono.empty();
                }));
    }
}

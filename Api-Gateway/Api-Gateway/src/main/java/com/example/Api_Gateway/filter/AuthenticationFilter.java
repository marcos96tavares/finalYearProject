package com.example.Api_Gateway.filter;

import jakarta.validation.constraints.AssertFalse;
import jakarta.ws.rs.core.HttpHeaders;
import jdk.jfr.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * AuthenticationFilter is a custom filter that extends AbstractGatewayFilterFactory
 * to handle authentication for incoming requests in the API Gateway.
 * 
 * This filter checks if the request is secure and contains a valid JWT token in the 
 * Authorization header. If the token is missing or invalid, the filter responds with 
 * an HTTP 401 Unauthorized status.
 * 
 * Dependencies:
 * - RouterValidator: Validates if the request needs to be authenticated.
 * - JwtTokenProvider: Provides methods to validate the JWT token.
 * 
 * Methods:
 * - AuthenticationFilter(): Constructor that initializes the filter with the Config class.
 * - app: Applies the filter logic to the incoming request.
 * 
 * Inner Class:
 * - Config: A placeholder class for any configuration properties needed by the filter.
 */
@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    @Autowired
    private RouterValidator routerValidator;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    public AuthenticationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            if (routerValidator.isSecure.test(exchange.getRequest())) {

                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    System.out.println("Authorization header not found");
                    exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                    return exchange.getResponse().setComplete();
                }

                String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    authHeader = authHeader.substring(7);
                }

                // Validate token
                if (!jwtTokenProvider.validateToken(authHeader)) {
                    exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                    return exchange.getResponse().setComplete();
                }
            }
            return chain.filter(exchange);
        };
    }

    public static class Config {
    }
}


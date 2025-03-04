package com.example.Api_Gateway.filter;


import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

/**
 * RouterValidator is a component that validates if a given request is secure.
 * It contains a list of open API endpoints that do not require security checks.
 * The isSecure predicate checks if the request URI does not start with any of the open API endpoints.
 */
@Component
public class RouterValidator {

    public static final List<String> openApiEndpoints = List.of(
            "api/login/user",
            "api/login/admin",
            "/api/membership"
    );

    public Predicate<ServerHttpRequest> isSecure =
            req -> openApiEndpoints.stream()
                    .noneMatch(req.getURI().getPath()::startsWith);
}

package com.unir.relatospapel.msgateway.decorator;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unir.relatospapel.msgateway.model.GatewayRequest;
import com.unir.relatospapel.msgateway.utils.CustomHttpHeaders;
import lombok.RequiredArgsConstructor;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * This class is responsible for creating decorators for the GatewayRequest object.
 * It uses the ObjectMapper to convert the raw request body into a GatewayRequest object.
 * Depending on the HTTP method of the request, it creates a different decorator.
 */
@Component
@RequiredArgsConstructor
public class RequestDecoratorFactory {

    private final ObjectMapper objectMapper;

    /**
     * This method creates a decorator for the GatewayRequest object.
     * It checks the HTTP method of the request and creates a different decorator depending on the method.
     * If the method is GET, it creates a GetRequestDecorator.
     * If the method is POST, it creates a PostRequestDecorator.
     * If the method is neither GET nor POST, it throws an IllegalArgumentException.
     *
     * @param request the GatewayRequest object to be decorated
     * @return a ServerHttpRequestDecorator that decorates the GatewayRequest object
     * @throws IllegalArgumentException if the HTTP method of the request is neither GET nor POST
     */
    public ServerHttpRequestDecorator getDecorator(GatewayRequest request) {
        return switch (Objects.requireNonNull(request.getHeaders().getFirst(CustomHttpHeaders.TARGET_METHOD)).toUpperCase()) {
            case "GET" -> new GetRequestDecorator(request);
            case "POST" -> new PostRequestDecorator(request, objectMapper);
            case "PUT" -> new PutRequestDecorator(request, objectMapper);
            case "PATCH" -> new PatchRequestDecorator(request, objectMapper);
            case "DELETE" -> new DeleteRequestDecorator(request);
            default -> throw new IllegalArgumentException("Invalid http method");
        };
    }
}

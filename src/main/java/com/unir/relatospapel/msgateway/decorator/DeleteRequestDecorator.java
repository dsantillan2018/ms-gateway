package com.unir.relatospapel.msgateway.decorator;

import com.unir.relatospapel.msgateway.model.GatewayRequest;
import lombok.NonNull;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;

import java.net.URI;

public class DeleteRequestDecorator extends ServerHttpRequestDecorator {

    private final GatewayRequest gatewayRequest;

    public DeleteRequestDecorator(GatewayRequest gatewayRequest) {
        super(gatewayRequest.getExchange().getRequest());
        this.gatewayRequest = gatewayRequest;
    }

    /**
     * This method overrides the getMethod method of the ServerHttpRequestDecorator class.
     * It returns the HTTP method of the request, which is DELETE.
     *
     * @return the HTTP method of the request
     */
    @Override
    @NonNull
    public HttpMethod getMethod() {
        return HttpMethod.DELETE;
    }

    /**
     * This method overrides the getURI method of the ServerHttpRequestDecorator class.
     * It returns the URI of the request
     *
     * @return the URI of the request
     */
    @Override
    @NonNull
    public URI getURI() {
        return UriComponentsBuilder
                .fromUri((URI) gatewayRequest.getExchange().getAttributes().get(ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR))
                .build()
                .toUri();
    }

    /**
     * This method overrides the getHeaders method of the ServerHttpRequestDecorator class.
     * It returns the headers of the request.
     *
     * @return the headers of the request
     */
    @Override
    @NonNull
    public HttpHeaders getHeaders() {
        return gatewayRequest.getHeaders();
    }

    /**
     * This method overrides the getBody method of the ServerHttpRequestDecorator class.
     * Since DELETE requests do not have a body, it returns an empty Flux of DataBuffers.
     *
     * @return an empty Flux of DataBuffers
     */
    @Override
    @NonNull
    public Flux<DataBuffer> getBody() {
        return Flux.empty();
    }
}

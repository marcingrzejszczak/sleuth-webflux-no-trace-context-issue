package com.example.webfluxnotracecontext;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
class TestHandler {

    private final TestRepository repository;

    TestHandler(TestRepository repository) {
        this.repository = repository;
    }

    /**
     * @should set the context
     */
    Mono<ServerResponse> save(ServerRequest request) {
        ServerWebExchange exchange = request.exchange();
        return request.bodyToMono(TestEntity.class)
                .map(testEntity -> testEntity.setContext(exchange))
                .flatMap(repository::save)
                .flatMap(entity -> ok().build());
    }
}

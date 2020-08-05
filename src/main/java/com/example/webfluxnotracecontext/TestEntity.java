package com.example.webfluxnotracecontext;

import brave.baggage.BaggageField;
import brave.internal.baggage.BaggageFields;
import brave.propagation.ExtraFieldPropagation;
import brave.propagation.TraceContext;

import org.springframework.cloud.sleuth.instrument.web.WebFluxSleuthOperators;
import org.springframework.web.server.ServerWebExchange;

public class TestEntity {

    private String field1;

    private String field2;

    public String getField1() {
        return field1;
    }

    public String getField2() {
        return field2;
    }

    public TestEntity() {
        super();
    }

    TestEntity(String field1) {
        this.field1 = field1;
    }

    TestEntity setContext(ServerWebExchange exchange) {
        TraceContext traceContext = WebFluxSleuthOperators.currentTraceContext(exchange);
        field2 = BaggageField.getByName(traceContext, "X-Field2").getValue();
        return this;
    }
}

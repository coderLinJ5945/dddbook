package com.dddbook.bank.base.api;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface MqEvent {
    String DEFAULT_ROUTE_KEY_HEADER = "x-route-key";
    String DEFAULT_ROUTE_TYPE_HEADER = "x-route-type";
    String CONDITION_EXPRESS = "headers['" + DEFAULT_ROUTE_TYPE_HEADER + "']=='";

    @JsonIgnore
    default String getRouterKey() {
        return getRouteType();
    }

    @JsonIgnore
    String getRouteType();

    @JsonIgnore
    default String getRouteKeyHeader() {
        return DEFAULT_ROUTE_KEY_HEADER;
    }

    @JsonIgnore
    default String getRouterTypeHeader() {
        return DEFAULT_ROUTE_TYPE_HEADER;
    }
}

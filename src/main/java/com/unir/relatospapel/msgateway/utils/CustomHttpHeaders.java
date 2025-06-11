package com.unir.relatospapel.msgateway.utils;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
public class CustomHttpHeaders extends HttpHeaders {
    public static final String TARGET_METHOD = "Target-Method";
}

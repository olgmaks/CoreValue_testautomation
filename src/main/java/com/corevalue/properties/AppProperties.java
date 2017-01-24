package com.corevalue.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:app.properties")
public class AppProperties {

    @Value("${base.url}")
    private String baseUrl;

    public String getBaseUrl() {
        return baseUrl;
    }
}

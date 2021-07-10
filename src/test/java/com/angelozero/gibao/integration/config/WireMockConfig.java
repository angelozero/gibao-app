package com.angelozero.gibao.integration.config;

import com.github.tomakehurst.wiremock.extension.Extension;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.cloud.contract.wiremock.WireMockConfigurationCustomizer;
import org.springframework.context.annotation.Bean;


@TestConfiguration
public class WireMockConfig {
    public WireMockConfig() {
    }

    @Bean
    public WireMockConfigurationCustomizer wireMockConfigurationCustomizer() {
        return (options) -> {

            options.extensions(new Extension[]{new NoKeepAliveTransformer()});
        };
    }

}

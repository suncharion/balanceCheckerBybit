package com.example.bybitbalance.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.web.reactive.function.client.WebClient

@Configuration
@EnableConfigurationProperties(BybitProperties::class)
class WebClientConfig {

    @Bean
    fun bybitWebClient(): WebClient = WebClient.builder()
        .baseUrl("https://api.bybit.com")
        .build()
}

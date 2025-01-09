package com.example.bybitbalance.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "bybit")
data class BybitProperties(
    val apiKey: String,
    val apiSecret: String
)

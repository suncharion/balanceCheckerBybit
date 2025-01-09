package com.example.bybitbalance.service

import com.example.bybitbalance.config.BybitProperties
import com.example.bybitbalance.util.generateBybitV5Signature
import com.example.bybitbalance.util.toQueryString
import kotlinx.coroutines.reactive.awaitSingle
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient

@Service
class BybitBalanceService(
    private val bybitWebClient: WebClient,
    private val bybitProperties: BybitProperties
) {

    suspend fun fetchWalletBalance() {
        val timestamp = System.currentTimeMillis().toString()
        val recvWindow = "5000"
        val params = mapOf("accountType" to "UNIFIED")

        val signature = generateBybitV5Signature(
            apiSecret = bybitProperties.apiSecret,
            timestamp = timestamp,
            apiKey = bybitProperties.apiKey,
            recvWindow = recvWindow,
            params = params
        )

        println("Timestamp: $timestamp")
        println("API Key: ${bybitProperties.apiKey}")
        println("Recv Window: $recvWindow")
        println("Params: $params")
        println("Generated Signature: $signature")

        runCatching {
            bybitWebClient.get()
                .uri("/v5/account/wallet-balance?${params.toQueryString()}")
                .header("X-BAPI-API-KEY", bybitProperties.apiKey)
                .header("X-BAPI-SIGN", signature)
                .header("X-BAPI-TIMESTAMP", timestamp)
                .header("X-BAPI-RECV-WINDOW", recvWindow)
                .header("X-BAPI-SIGN-TYPE", "2")
                .retrieve()
                .bodyToMono(String::class.java)
                .awaitSingle()
        }.onSuccess { response ->
            println("Balance: $response\n")
        }.onFailure { ex ->
            println("Error fetch balance: ${ex.message}\n")
        }
    }
}

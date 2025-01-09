package com.example.bybitbalance.util

import java.nio.charset.StandardCharsets
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

/**
 * Генерация подписи
 * Формула: "$timestamp$apiKey$recvWindow$queryString"
 */
fun generateBybitV5Signature(
    apiSecret: String,
    timestamp: String,
    apiKey: String,
    recvWindow: String,
    params: Map<String, Any>
): String {
    val queryString = params.toSortedMap().toQueryString()
    val stringToSign = "$timestamp$apiKey$recvWindow$queryString"

    println("Sign: $stringToSign")

    val mac = Mac.getInstance("HmacSHA256").apply {
        init(SecretKeySpec(apiSecret.toByteArray(StandardCharsets.UTF_8), "HmacSHA256"))
    }
    return mac.doFinal(stringToSign.toByteArray(StandardCharsets.UTF_8)).toHex()
}

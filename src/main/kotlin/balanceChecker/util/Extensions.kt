package com.example.bybitbalance.util

fun ByteArray.toHex(): String =
    joinToString("") { "%02x".format(it) }

fun Map<String, Any>.toQueryString(): String =
    entries.joinToString("&") { "${it.key}=${it.value}" }

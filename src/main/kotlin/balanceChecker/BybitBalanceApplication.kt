package com.example.bybitbalance

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class BybitBalanceApplication

fun main(args: Array<String>) {
    runApplication<BybitBalanceApplication>(*args)
}

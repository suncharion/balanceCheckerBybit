package com.example.bybitbalance.scheduler

import com.example.bybitbalance.service.BybitBalanceService
import kotlinx.coroutines.runBlocking
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class BalanceScheduler(
    private val bybitBalanceService: BybitBalanceService
) {

    @Scheduled(fixedRate = 5000)  // каждые 5 секунд
    fun scheduleFetchBalance() = runBlocking {
        bybitBalanceService.fetchWalletBalance()
    }
}

package com.ecom.tech.ordermanagementservice.controller.admin.scheduler

import com.ecom.tech.ordermanagementservice.service.OrderService
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

// TODO: implement logic
@Component
class DataCleanUpScheduler(private val orderService: OrderService) {

    @Scheduled(cron = "0 0 0 * * MON") // Каждый понедельник в полночь
    fun cleanOldOrders() {
        orderService.cleanUp()
    }
}
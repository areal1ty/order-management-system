package com.ecom.tech.ordermanagementservice.model.dto

import java.time.LocalDateTime

data class PaymentDTO (
    val id: Long,
    val transaction: String,
    val currency: String,
    val provider: String,
    val amount: Int,
    val paymentDt: LocalDateTime,
    val bank: String,
    val deliveryCost: Int,
    val goodsTotal: Int,
    val customFee: Int,
    val status: String
)
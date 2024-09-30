package com.ecom.tech.ordermanagementservice.model.dto

import com.ecom.tech.ordermanagementservice.model.entity.status.PaymentStatus

data class PaymentDTO (
    var transaction: String,
    var currency: String,
    var provider: String,
    var amount: Int,
    var paymentDt: Long,
    var bank: String,
    var deliveryCost: Int,
    var goodsTotal: Int,
    var customFee: Int,
    var status: PaymentStatus
)
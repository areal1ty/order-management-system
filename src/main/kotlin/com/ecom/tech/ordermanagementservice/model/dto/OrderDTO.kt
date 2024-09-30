package com.ecom.tech.ordermanagementservice.model.dto

import com.ecom.tech.ordermanagementservice.model.entity.Order
import com.ecom.tech.ordermanagementservice.model.entity.status.OrderStatus

data class OrderDTO (
    val orderUid: String,
    var trackNumber: String,
    val entry: String,
    val delivery: DeliveryDTO,
    val payment: PaymentDTO,
    val items: List<ItemDTO>,
    val deliveryService: String,
    val dateCreated: String,
    val status: OrderStatus
)

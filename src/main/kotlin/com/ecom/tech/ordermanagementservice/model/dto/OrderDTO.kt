package com.ecom.tech.ordermanagementservice.model.dto

data class OrderDTO (
    val orderId: Long,
    val trackNumber: String,
    val entry: String,
    val delivery: DeliveryDTO,
    val payment: PaymentDTO,
    val items: List<ItemDTO>,
    val deliveryService: String,
    val dateCreated: String,
    val status: String
)

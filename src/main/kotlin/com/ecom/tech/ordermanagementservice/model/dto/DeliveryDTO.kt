package com.ecom.tech.ordermanagementservice.model.dto

data class DeliveryDTO(
    val id: Long,
    val name: String,
    val phone: String,
    val zip: String,
    val city: String,
    val address: String,
    val region: String,
    val email: String
)

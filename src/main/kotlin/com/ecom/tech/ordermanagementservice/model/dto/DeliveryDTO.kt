package com.ecom.tech.ordermanagementservice.model.dto

data class DeliveryDTO(
    var name: String,
    var phone: String,
    var zip: String,
    var city: String,
    var address: String,
    var region: String,
    var email: String
)

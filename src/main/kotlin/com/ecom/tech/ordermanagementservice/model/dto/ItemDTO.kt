package com.ecom.tech.ordermanagementservice.model.dto

data class ItemDTO (
    var id: Long,
    var chrtId: Int,
    var trackNumber: String,
    var price: Int,
    var rid: String,
    var name: String,
    var sale: Int,
    var size: String,
    var totalPrice: Int,
    var nmId: Int,
    var brand: String,
    var orderUid: String,
)
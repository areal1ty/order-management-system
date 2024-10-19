package main.kotlin.model.dto

data class ItemDTO (
    val id: Long,
    val chrtId: Int,
    val trackNumber: String,
    val price: Int,
    val rid: String,
    val name: String,
    val sale: Int,
    val size: String,
    val totalPrice: Int,
    val nmId: Int,
    val brand: String,
    val status: String,
    val orderId: Long?
)
package com.ecom.tech.ordermanagementservice.model.entity

import jakarta.persistence.*

@Entity
@Table(name = "items")
data class Item(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    @Column(name = "chrt_id")
    var chrtId: Int,
    @Column(name = "track_number")
    var trackNumber: String,
    var price: Int,
    var rid: String,
    var name: String,
    var sale: Int,
    var size: String,
    var totalPrice: Int,
    var nmId: Int,
    var brand: String,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_uid")
    var order: Order?
)

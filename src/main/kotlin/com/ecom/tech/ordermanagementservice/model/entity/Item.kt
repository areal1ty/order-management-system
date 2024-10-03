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

    @Column(name = "price")
    var price: Int,

    @Column(name = "rid")
    var rid: String,

    @Column(name = "name")
    var name: String,

    @Column(name = "sale")
    var sale: Int,

    @Column(name = "size")
    var size: String,

    @Column(name = "total_price")
    var totalPrice: Int,

    @Column(name = "nm_id")
    var nmId: Int,

    @Column(name = "brand")
    var brand: String,

    @Column(name = "status")
    var status: Int,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
    var order: Order? = null
)

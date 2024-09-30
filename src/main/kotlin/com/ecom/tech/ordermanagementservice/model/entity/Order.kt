package com.ecom.tech.ordermanagementservice.model.entity

import com.ecom.tech.ordermanagementservice.model.entity.status.OrderStatus
import jakarta.persistence.*

@Entity
@Table(name = "orders")
data class Order(
    @Id
    @Column(name = "order_uid")
    var orderUid: String,

    @Column(name = "track_number")
    var trackNumber: String,

    @Column(name = "entry")
    var entry: String,

    @OneToOne(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id")
    var delivery: Delivery,

    @OneToOne(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_id")
    var payment: Payment,

    @OneToMany(mappedBy = "order", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    var items: List<Item>,

    @Column(name = "delivery_service")
    var deliveryService: String,

    @Column(name = "date_created")
    var dateCreated: String,

    @Column(name = "status")
    var status: OrderStatus
)
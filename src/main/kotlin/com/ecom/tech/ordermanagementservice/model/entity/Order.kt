package com.ecom.tech.ordermanagementservice.model.entity

import com.ecom.tech.ordermanagementservice.model.entity.status.OrderStatus
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "orders")
data class Order(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    var orderId: Long,

    @Column(name = "track_number")
    var trackNumber: String,

    @Column(name = "entry")
    var entry: String,

    @OneToOne(mappedBy = "order", cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    var delivery: Delivery,

    @OneToOne(mappedBy = "order", cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    var payment: Payment,

    @OneToMany(mappedBy = "order", cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    var items: List<Item>,

    @Column(name = "delivery_service")
    var deliveryService: String,

    @Column(name = "date_created")
    var dateCreated: LocalDateTime,

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    var status: OrderStatus = OrderStatus.NEW
)
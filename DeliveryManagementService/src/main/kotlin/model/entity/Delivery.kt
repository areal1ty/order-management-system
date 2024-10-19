package com.ecom.tech.ordermanagementservice.model.entity
import jakarta.persistence.*

@Entity
@Table(name = "delivery")
data class Delivery(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_id")
    var id: Long = 0,

    @Column(name = "name")
    var name: String,

    @Column(name = "phone")
    var phone: String,

    @Column(name = "zip")
    var zip: String,

    @Column(name = "city")
    var city: String,

    @Column(name = "address")
    var address: String,

    @Column(name = "region")
    var region: String,

    @Column(name = "email")
    var email: String,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    var order: Order?

)

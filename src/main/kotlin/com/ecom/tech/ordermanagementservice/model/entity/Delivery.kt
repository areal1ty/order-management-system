package com.ecom.tech.ordermanagementservice.model.entity
import jakarta.persistence.*;

@Entity
@Table(name = "delivery")
data class Delivery(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    var name: String,
    var phone: String,
    var zip: String,
    var city: String,
    var address: String,
    var region: String,
    var email: String
)

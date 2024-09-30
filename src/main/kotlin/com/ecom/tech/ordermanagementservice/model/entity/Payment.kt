package com.ecom.tech.ordermanagementservice.model.entity

import com.ecom.tech.ordermanagementservice.model.entity.status.PaymentStatus
import jakarta.persistence.*

@Entity
@Table(name = "payment")
data class Payment(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    var transaction: String,
    var currency: String,
    var provider: String,
    var amount: Int,
    var paymentDt: Long,
    var bank: String,
    var deliveryCost: Int,
    var goodsTotal: Int,
    var customFee: Int,
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    var status: PaymentStatus = PaymentStatus.NEW
)

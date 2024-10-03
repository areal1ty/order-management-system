package com.ecom.tech.ordermanagementservice.model.entity

import com.ecom.tech.ordermanagementservice.model.entity.status.PaymentStatus
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "payment")
data class Payment(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    var id: Long = 0,

    @Column(name = "transaction")
    var transaction: String,

    @Column(name = "currency")
    var currency: String,

    @Column(name = "provider")
    var provider: String,

    @Column(name = "amount")
    var amount: Int,

    @Column(name = "payment_dt")
    var paymentDt: LocalDateTime,

    @Column(name = "bank")
    var bank: String,

    @Column(name = "delivery_cost")
    var deliveryCost: Int,

    @Column(name = "goods_total")
    var goodsTotal: Int,

    @Column(name = "custom_fee")
    var customFee: Int,

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    var status: PaymentStatus = PaymentStatus.NEW,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    var order: Order?
)

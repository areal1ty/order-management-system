package com.ecom.tech.ordermanagementservice.model.mapper

import com.ecom.tech.ordermanagementservice.model.dto.PaymentDTO
import com.ecom.tech.ordermanagementservice.model.entity.Payment

object PaymentDtoMapper {
    fun toEntity(paymentDto: PaymentDTO) = Payment(
        transaction = paymentDto.transaction,
        currency = paymentDto.currency,
        provider = paymentDto.provider,
        amount = paymentDto.amount,
        paymentDt = paymentDto.paymentDt,
        bank = paymentDto.bank,
        deliveryCost = paymentDto.deliveryCost,
        goodsTotal = paymentDto.goodsTotal,
        customFee = paymentDto.customFee,
        status = paymentDto.status
    )

    fun toDto(payment: Payment) = PaymentDTO(
        transaction = payment.transaction,
        currency = payment.currency,
        provider = payment.provider,
        amount = payment.amount,
        paymentDt = payment.paymentDt,
        bank = payment.bank,
        deliveryCost = payment.deliveryCost,
        goodsTotal = payment.goodsTotal,
        customFee = payment.customFee,
        status = payment.status
    )
}
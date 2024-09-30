package com.ecom.tech.ordermanagementservice.model.entity.status

import com.fasterxml.jackson.annotation.JsonValue

enum class PaymentStatus(@JsonValue val value: String) {
    NEW("NEW"),
    PENDING("PENDING"),
    COMPLETED("COMPLETED"),
    FAILED("FAILED"),
    CANCELLED("CANCELLED");

    companion object {
        fun fromValue(value: String): PaymentStatus {
            return entries.find { it.value == value }
                ?: throw IllegalArgumentException("Unknown payment status: $value")
        }
    }
}